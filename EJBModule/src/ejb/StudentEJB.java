package ejb;

import com.fasterxml.jackson.databind.ObjectMapper;
import data.Course;
import data.Student;
import dto.CourseDTO;
import dto.UserDTO;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
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

    public ArrayList<UserDTO> getStudents() {
        try {
            Query query = entityManager.createQuery("SELECT students FROM Student students");
            ArrayList<Student> students = (ArrayList<Student>) query.getResultList();


            return getListOfStudentDTO(students);
        } catch (Exception e) {
            logger.error("CourseEJB: Error getting courses: " + e.getMessage());
        }
        return null;
    }

    private ArrayList<UserDTO> getListOfStudentDTO(ArrayList<Student> students) {
        ArrayList<UserDTO> studentsDTO = new ArrayList<UserDTO>();
        for(Student student : students) {
            studentsDTO.add(getStudentDTOFromEntity(student));
        }
        return studentsDTO;
    }

    private UserDTO getStudentDTOFromEntity(Student student) {
        UserDTO studentDTO = new UserDTO();

        studentDTO.setUserId(student.getUserId());
        studentDTO.setName(student.getName());
        studentDTO.setInstitutionalEmail(student.getInstitutionalEmail());

        return studentDTO;
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

    public String searchStudent(String reference){

        Student studentResult;

        try {
            Query newQuery = entityManager.createQuery("FROM User user where user.userType=?1 and (user.name=?2 or user.institutionalEmail=?3 or user.email=?4)");
            newQuery.setParameter(1,"STUDENT");
            newQuery.setParameter(2, reference);
            newQuery.setParameter(3, reference);
            newQuery.setParameter(4, reference);

            studentResult = (Student) newQuery.getSingleResult();

            if(studentResult != null) {
                return studentResult.getUserId();
            }
        } catch (Exception e){
            logger.info("StudentEJB: Exception caught trying to search for student. Exception: " + e.getMessage());
        }
        return null;
    }

}
