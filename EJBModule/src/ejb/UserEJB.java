package ejb;

import com.fasterxml.jackson.databind.ObjectMapper;
import data.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class UserEJB implements UserEJBRemote {

    @PersistenceContext(name="Coursify")
    private EntityManager entityManager;

    @EJB
    AuthEJBRemote authEJB;

    static final Logger logger = LogManager.getLogger(UserEJB.class);
    static final ObjectMapper mapper = new ObjectMapper();

    public String getUserType(String sessionToken) {
        logger.debug(">>>> Getting user type <<<<");

        Token token = authEJB.getSessionToken(sessionToken);
        User user = token.getUser();

        return user.getUserType();
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
}
