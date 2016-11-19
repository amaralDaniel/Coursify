package ejb;

import data.Course;
import data.Professor;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CourseEJB implements CourseEJBRemote {
    @PersistenceContext(name="Coursify")
    EntityManager entityManager;

    private final Logger logger = Logger.getLogger(AuthEJB.class);

    public boolean createCourse(String name, String description, Integer professorId) {
        logger.debug(">>>> CourseEJB: Creating course <<<<");
        Course course = null;
        Professor professor = entityManager.find(Professor.class, professorId);

        if(professor != null) {
            course = new Course(name, description, professor);
            entityManager.persist(course);
            logger.info("New course created");
        }

        return true;
    }

    public boolean readCourse(String courseId) {
        logger.debug(">>>> CourseEJB: Reading course <<<<");
        Course course = entityManager.find(Course.class, courseId);

        return true;
    }

    public boolean updateCourse() {
        return true;
    }

    public boolean deleteCourse() {
        return true;
    }
}
