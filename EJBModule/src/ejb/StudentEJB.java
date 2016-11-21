package ejb;

import com.fasterxml.jackson.databind.ObjectMapper;
import data.Course;
import data.Student;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

/**
 * Created by danielamaral on 21/11/2016.
 */
public class StudentEJB implements StudentEJBRemote {
    @PersistenceContext(name="Coursify")
    EntityManager entityManager;

    CourseEJBRemote courseEJB;

    private final Logger logger = Logger.getLogger(CourseEJB.class);
    static final ObjectMapper mapper = new ObjectMapper();

    public Student getStudent(int studentId){
        try {
            Student studentToOutput = entityManager.find(Student.class, studentId);

            logger.info("StudentEJB: got student");
            return studentToOutput;
        }catch(Exception ex){
            logger.info("StudentEJB: Error fetching course. Exception: " + ex.getMessage());
        }
        return null;
    }

    public String listStudentsAlphabetically(int courseId){
        List studentsList;
        Course course;

        try{
            course = courseEJB.getCourse(courseId);
            studentsList = course.getStudentsList();

            Collections.sort(studentsList);

            logger.info("StudentEJB: sorted list of students sent");
            return mapper.writeValueAsString(studentsList);
        }catch(Exception ex){
            logger.info("StudentEJB: Error fetching students. Exception: " + ex.getMessage());
        }
        return null;
    }


}
