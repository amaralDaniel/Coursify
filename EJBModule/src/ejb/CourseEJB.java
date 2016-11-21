package ejb;

import com.fasterxml.jackson.databind.ObjectMapper;
import data.Course;
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

    private final Logger logger = Logger.getLogger(CourseEJB.class);
    static final ObjectMapper mapper = new ObjectMapper();

    public boolean createCourse(String name, String description, Integer professorId) {
        logger.debug(">>>> CourseEJB: Creating course <<<<");
        Course course;
        Professor professor;

        try {
            professor = getProfessor(professorId);

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

    public Professor getProfessor(int professorId) {
        logger.debug(">>>> CourseEJB: Fetching Professor<<<<");
        try {
            Professor professor = entityManager.find(Professor.class, professorId);

            return professor;
        } catch (Exception e) {
            logger.error("CourseEJB: Error getting professor");
        }
        return null;
    }

    public boolean readCourse(String courseId) {
        logger.debug(">>>> CourseEJB: Reading course <<<<");
        Course course = entityManager.find(Course.class, courseId);


        return true;
    }

    public boolean updateCourse() {
        return false;
    }

    public boolean deleteCourse() {
        return true;
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
