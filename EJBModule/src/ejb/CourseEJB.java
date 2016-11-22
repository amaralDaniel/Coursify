package ejb;

import com.fasterxml.jackson.databind.ObjectMapper;
import data.*;
import dto.CourseDTO;
import dto.UserDTO;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless(name="CourseEJB")
public class CourseEJB implements CourseEJBRemote {
    @PersistenceContext(name="Coursify")
    EntityManager entityManager;

    @EJB
    ProfessorEJBRemote professorEJB;
    @EJB
    StudentEJBRemote studentEJB;

    private final Logger logger = Logger.getLogger(CourseEJB.class);
    static final ObjectMapper mapper = new ObjectMapper();

    public boolean createCourse(String name, String description, String professorId) {
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

    public boolean addStudentToCourse (String courseId, String studentId){
        logger.debug(">>>> CourseEJB: Add stutdent to course <<<<");
        Course courseToAddStudent;
        Student studentToAdd;

        try{
            courseToAddStudent = getCourseEntity(courseId);
            studentToAdd = studentEJB.getStudent(studentId);

            courseToAddStudent.getStudentsList().add(studentToAdd);
            entityManager.persist(courseToAddStudent);
            logger.info("CourseEJB: Student added to desired course");
            return true;
        }catch (Exception ex){
            logger.error("CourseEJB: Error adding student to course. Exception: " + ex.getMessage());
        }

        return false;
    }

    public String readCourse(String courseId) {

        logger.debug(">>>> CourseEJB: Reading course <<<<");
        Course courseToOutput;
        try {
            courseToOutput= getCourseEntity(courseId);

            logger.debug("AuthEJB: read material");
            return mapper.writeValueAsString(courseToOutput);
        }catch (Exception ex){
            logger.info("Something went wrong when trying to read a material. Exception: " + ex.getMessage());
        }

        return null;

    }

    public boolean updateCourse(CourseDTO course) {
        logger.debug(">>>> CourseEJB: Updating course <<<<");

        try{
            Course courseEntity = entityManager.find(Course.class, course.getCourseId());

            courseEntity.setName(course.getName());
            courseEntity.setDescription(course.getDescription());

            return true;
        }catch (Exception e) {
            logger.error("CourseEJB: Error updating course: " + e.getMessage());
        }

        return false;
    }

    public boolean deleteCourse(String courseId) {
        logger.debug(">>>> CourseEJB: Deleting course <<<<");

        try{
            Course courseToRemove = getCourseEntity(courseId);
            entityManager.remove(courseToRemove);

            logger.debug("Course found and removed");
            return true;
        }catch (Exception ex){
            logger.error("Something went wrong when trying to delete a course. Exception: " + ex.getMessage());
        }
        return false;
    }

    public Course getCourseEntity(String courseId) {

        try {
            Course courseToAssign = entityManager.find(Course.class, courseId);
            return courseToAssign;
        }catch(Exception ex){
            logger.error("CourseEJB: Error fetching course. Exception: " + ex.getMessage());
        }
        return null;
    }

    public CourseDTO getCourse(String courseId) {
        try {
            return getCourseDTOFromEntity(getCourseEntity(courseId));
        } catch (Exception e) {
            logger.error("CourseEJB: Error getting course: " + e.getMessage());
        }
        return null;
    }

    private CourseDTO getCourseDTOFromEntity(Course course) throws Exception {
        CourseDTO courseDTO = new CourseDTO();

        courseDTO.setCourseId(course.getCourseId());
        courseDTO.setName(course.getName());
        courseDTO.setDescription(course.getDescription());

        return courseDTO;
    }

    public String getCourses(String sessionToken) {
        //TODO: Limit access to user through session token
        try {
            Query query = entityManager.createQuery("SELECT courses FROM Course courses");
            List<Course> courses = (List<Course>) query.getResultList();
            return mapper.writeValueAsString(courses);
        } catch (Exception e) {
            logger.error("CourseEJB: Error getting courses: " + e.getMessage());
        }
        return null;
    }


}
