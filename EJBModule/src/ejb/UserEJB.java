package ejb;

import com.fasterxml.jackson.databind.ObjectMapper;
import data.*;
import dto.UserDTO;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Stateless(name="UserEJB")
public class UserEJB implements UserEJBRemote {

    @PersistenceContext(name="Coursify")
    private EntityManager entityManager;

    @EJB
    AuthEJBRemote authEJB;
    @EJB
    MaterialEJBRemote materialEJB;
    @EJB
    CourseEJBRemote courseEJB;

    static final Logger logger = LogManager.getLogger(UserEJB.class);
    static final ObjectMapper mapper = new ObjectMapper();

    public String getUserType(String sessionToken) {
        logger.debug(">>>> Getting user type <<<<");

        Token token = authEJB.getSessionToken(sessionToken);
        User user = token.getUser();

        return user.getUserType();
    }

    public String getUserId(String sessionToken) {
        logger.debug(">>>> Getting user type <<<<");

        Token token = authEJB.getSessionToken(sessionToken);
        User user = token.getUser();

        return user.getUserId();
    }

    public String getAllUsers(String sessionToken) {
        logger.info(">>>> Getting All Users <<<<");
        String userType = getUserType(sessionToken);

        if(userType.equals("ADMINISTRATOR")) {
            List<User> users = getUsers();
            try {
                return mapper.writeValueAsString(users);
            } catch (Exception e) {
                logger.error("UserEJB: Error transforming users: " + e.getMessage());
            }
        }
        return null;
    }

    public String getStudents(String sessionToken) {
        logger.info(">>>> Getting Students <<<<");
        String userType = getUserType(sessionToken);

        if(userType.equals("PROFESSOR")) {
            List<Student> students = getStudents();
            try {
                return mapper.writeValueAsString(students);
            } catch (Exception e) {
                logger.error("UserEJB: Error transforming students: " + e.getMessage());
            }
        }
        return null;
    }

    public String getProfessors(String sessionToken) {
        logger.info(">>>> Getting Professors <<<<");

        String userType = getUserType(sessionToken);

        if(userType.equals("STUDENT")) {
            List<Professor> professors = getProfessors();
            try {
                return mapper.writeValueAsString(professors);
            } catch (Exception e) {
                logger.error("UserEJB: Error transforming professors: " + e.getMessage());
            }
        }
        return null;
    }

    private List<User> getUsers() {
        try {
            Query query = entityManager.createQuery("SELECT users FROM User users");
            return (List<User>) query.getResultList();
        } catch (Exception e) {
            logger.error("UserEJB: Error getting users: " + e.getMessage());
        }
        return null;
    }

    private List<Professor> getProfessors() {
        try {
            Query query = entityManager.createQuery("SELECT professors FROM Professor professors", Professor.class);
            return (List<Professor>) query.getResultList();
        } catch (Exception e) {
            logger.error("UserEJB: Error getting professors: " + e.getMessage());
        }
        return null;
    }

    private List<Student> getStudents() {
        try {
            Query query = entityManager.createQuery("SELECT students FROM Student students", Student.class);
            return (List<Student>) query.getResultList();
        } catch (Exception e) {
            logger.error("UserEJB: Error getting students: " + e.getMessage());
        }
        return null;
    }

    public UserDTO getUser(String sessionToken, String userId) {
        logger.info(">>>> Getting User <<<<");
        String userType = getUserType(sessionToken);

        if(userType.equals("ADMINISTRATOR")) {
            User user = getAccount(userId);
            logger.info("USER: " + user.toString());
            try {
                return getUserDTOFromEntity(user);
            } catch (Exception e) {
                logger.error("UserEJB: Error getting user from ADMINISTRATOR: " + e.getMessage());
            }
        } else {
            try {
                User user = getAccount(userId);

                if(user.getUserId().equals(userId)) {
                    return getUserDTOFromEntity(user);
                }
                return null;
            } catch (Exception e) {
                logger.error("UserEJB: Error getting user from PROFESSOR or STUDENT: " + e.getMessage());
            }
        }
        return null;
    }

    private UserDTO getUserDTOFromEntity(User user) throws Exception {
        UserDTO userDTO = new UserDTO();
        String userType = user.getUserType();

        userDTO.setUserId(user.getUserId());
        userDTO.setUserType(user.getUserType());
        userDTO.setNumber(user.getNumber());
        userDTO.setName(user.getName());
        userDTO.setBirthdate(user.getBirthdate());
        userDTO.setInstitutionalEmail(user.getInstitutionalEmail());
        userDTO.setEmail(user.getEmail());
        userDTO.setAddress(user.getAddress());
        userDTO.setTelephone(user.getTelephone());

        if (userType.equals("PROFESSOR")) {
            Professor professor = (Professor) user;

            userDTO.setInternalTelephone(professor.getInternalTelephone());
            userDTO.setCategory(professor.getCategory());
            userDTO.setOffice(professor.getOffice());
            userDTO.setInternalTelephone(professor.getInternalTelephone());
            userDTO.setSalary(professor.getSalary());
        }

        logger.debug("UserDTO created: " + userDTO.toString());

        return userDTO;
    }

    private User getAccount(String userId) {
        try {
            User user = entityManager.find(User.class, userId);

            if(user == null) {
                logger.warn("UserEJB: User not found");
            }
            return user;
        } catch (Exception e) {
            logger.error("UserEJB: Error getting account: " + e.getMessage());
        }
        return null;
    }

    public boolean updateUser(UserDTO user) {
        logger.info(">>>> Updating User <<<<");
        String userType = user.getUserType();
        try {
            if (userType.equals("ADMINISTRATOR")) {
                Administrator administrator = entityManager.find(Administrator.class, user.getUserId());

                administrator.setName(user.getName());
                administrator.setBirthdate(user.getBirthdate());
                administrator.setInstitutionalEmail(user.getInstitutionalEmail());
                administrator.setEmail(user.getEmail());
                administrator.setAddress(user.getAddress());
                administrator.setTelephone(user.getTelephone());

                return true;
            } else if (userType.equals("PROFESSOR")) {

            } else if (userType.equals("STUDENT")) {

            }
        } catch (Exception e) {
            logger.error("UserEJB: Error updating user " + e.getMessage());
        }
        return false;
    }

    public boolean deleteUser(String sessionToken, String userId) {
        logger.info(">>>> Deleting User <<<<");
        String userType = getUserType(sessionToken);

        if(userType.equals("ADMINISTRATOR")) {
            try {
                User user = entityManager.find(User.class, userId);

                if(user != null) {
                    Query query = entityManager.createQuery("FROM Material material WHERE material.author.userId = ?1");
                    query.setParameter(1, userId);
                    ArrayList<Material> materials = (ArrayList<Material>) query.getResultList();

                    query = entityManager.createQuery("FROM Course course WHERE course.professor.userId = ?1");
                    query.setParameter(1, userId);
                    ArrayList<Course> courses = (ArrayList<Course>) query.getResultList();

                    deleteMaterials(materials);
                    deleteCourses(courses);

                    entityManager.remove(user);
                    return true;
                }
            } catch (Exception e) {
                logger.error("UserEJB: Error deleting user: " + e.getMessage());
            }
        }
        return false;
    }

    private void deleteMaterials(ArrayList<Material> materials) {
        for(Material material : materials) {
            entityManager.remove(material);
        }
    }

    private void deleteCourses(ArrayList<Course> courses) {
        for(Course course : courses) {
            entityManager.remove(course);
        }
    }
}
