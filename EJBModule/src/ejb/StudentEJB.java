package ejb;

import com.fasterxml.jackson.databind.ObjectMapper;
import data.Course;
import data.Student;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

@Stateless(name="StudentEJB")
public class StudentEJB implements StudentEJBRemote {
    @PersistenceContext(name="Coursify")
    EntityManager entityManager;

    CourseEJBRemote courseEJB;

    private final Logger logger = Logger.getLogger(CourseEJB.class);
    static final ObjectMapper mapper = new ObjectMapper();

    public Student getStudent(String studentId){
        try {
            Student studentToOutput = entityManager.find(Student.class, studentId);

            logger.info("StudentEJB: got student");
            return studentToOutput;
        }catch(Exception ex){
            logger.info("StudentEJB: Error fetching course. Exception: " + ex.getMessage());
        }
        return null;
    }



    public String listStudentsAlphabetically(String courseId){
        List studentsList;
        Course course;

        try{
            course = courseEJB.getCourseEntity(courseId);
            studentsList = course.getStudentsList();

            Collections.sort(studentsList);

            logger.info("StudentEJB: sorted list of students sent");
            return mapper.writeValueAsString(studentsList);
        }catch(Exception ex){
            logger.info("StudentEJB: Error fetching students. Exception: " + ex.getMessage());
        }
        return null;
    }

    public List<Student> searchStudent(String reference){

        List<Student> searchResults;

        try {
            Query newQuery = entityManager.createQuery("FROM User user where user.userType=?1 and (user.name=?2 or user.institutionalEmail=?3 or user.email=?4)");
            newQuery.setParameter(1,"STUDENT");
            newQuery.setParameter(2, reference);
            newQuery.setParameter(3, reference);
            newQuery.setParameter(4, reference);

            searchResults = newQuery.getResultList();

            return searchResults;
        } catch (Exception e){
            logger.info("StudentEJB: Exception caught trying to search for student. Exception: " + e.getMessage());
        }
        return null;
    }


}
