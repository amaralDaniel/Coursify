package ejb;

import data.Student;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless(name="StudentEJB")
public class StudentEJB implements StudentEJBRemote {
    @PersistenceContext(name="Coursify")
    EntityManager entityManager;

    private final Logger logger = Logger.getLogger(CourseEJB.class);

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
}
