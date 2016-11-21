package ejb;

import com.fasterxml.jackson.databind.ObjectMapper;
import data.Course;
import data.Material;
import data.Professor;
import org.apache.log4j.Logger;
import org.hibernate.mapping.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless(name="CourseEJB")
public class CourseEJB implements CourseEJBRemote {
    @PersistenceContext(name="Coursify")
    EntityManager entityManager;
    ProfessorEJBRemote professorEJB;

    private final Logger logger = Logger.getLogger(CourseEJB.class);
    static final ObjectMapper mapper = new ObjectMapper();

    public boolean createCourse(String name, String description, Integer professorId) {
        logger.debug(">>>> CourseEJB: Creating course <<<<");
        Course course;
        Professor professor;

        try {
            professor = professorEJB.getProfessor(professorId);

            if(professor != null) {
                course = new Course(name, description, professor);

                entityManager.persist(course);
                return true;
            }
        } catch (Exception e) {
            logger.error("CourseEJB: Error creating course");
        }
        return false;
    }

    public String readCourse(int courseId) {

        logger.debug(">>>> MaterialEJB: Reading course <<<<");

        try {
            Course courseToOutput= getCourse(courseId);

            logger.debug("AuthEJB: read material");
            return mapper.writeValueAsString(courseToOutput);
        }catch (Exception ex){
            logger.info("Something went wrong when trying to read a material. Exception: " + ex.getMessage());
        }

        return null;

    }

    public boolean updateCourse(String materialObjectMapper) {
        logger.debug(">>>> MaterialEJB: Updating material <<<<");

        try{
            Material materialToUpdate = mapper.readValue(materialObjectMapper,Material.class);
            entityManager.persist(materialToUpdate);
            return true;
        }catch (Exception ex){
            logger.info("Something went wrong when trying to update a material. Exception: " + ex.getMessage());
        }

        return false;
    }

    public boolean deleteCourse(int materialId){
        logger.debug(">>>> MaterialEJB: Deleting material <<<<");

        try{
            Course courseToRemove = getCourse(materialId);
            entityManager.remove(courseToRemove);

            logger.debug("Course found and removed");
            return true;
        }catch (Exception ex){
            logger.info("Something went wrong when trying to delete a course. Exception: " + ex.getMessage());
        }
        return false;
    }

    public Course getCourse (int courseId){

        try {
            Course courseToAssign = entityManager.find(Course.class, courseId);
            return courseToAssign;
        }catch(Exception ex){
            logger.info("MaterialEJB: Error fetching course. Exception: " + ex.getMessage());
        }
        return null;
    }

    public String getCourses(String sessionToken) {
        //TODO: Limit access to user through session token
        try {
            Query query = entityManager.createQuery("SELECT courses FROM Course courses");
            Collection courses = (Collection) query.getResultList();
            return mapper.writeValueAsString(courses);
        } catch (Exception e) {
            logger.error("CourseEJB: Error getting courses: " + e.getMessage());
        }
        return null;
    }


}
