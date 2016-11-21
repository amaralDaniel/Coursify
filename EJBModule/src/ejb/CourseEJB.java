package ejb;

import com.fasterxml.jackson.databind.ObjectMapper;
import data.Course;
import data.Material;
import data.Professor;
import data.Student;
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
            courseToAddStudent = getCourse(courseId);
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
            courseToOutput= getCourse(courseId);

            logger.debug("AuthEJB: read material");
            return mapper.writeValueAsString(courseToOutput);
        }catch (Exception ex){
            logger.info("Something went wrong when trying to read a material. Exception: " + ex.getMessage());
        }

        return null;

    }

    public boolean updateCourse(String materialObjectMapper) {
        logger.debug(">>>> CourseEJB: Updating material <<<<");

        try{
            Material materialToUpdate = mapper.readValue(materialObjectMapper,Material.class);
            entityManager.persist(materialToUpdate);
            return true;
        }catch (Exception ex){
            logger.info("Something went wrong when trying to update a material. Exception: " + ex.getMessage());
        }

        return false;
    }

    public boolean deleteCourse(String materialId){
        logger.debug(">>>> CourseEJB: Deleting material <<<<");

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

    public Course getCourse (String courseId){

        try {
            Course courseToAssign = entityManager.find(Course.class, courseId);
            return courseToAssign;
        }catch(Exception ex){
            logger.info("CourseEJB: Error fetching course. Exception: " + ex.getMessage());
        }
        return null;
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
