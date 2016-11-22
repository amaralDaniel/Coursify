package ejb;

import data.Student;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface StudentEJBRemote {

    Student getStudent(String studentId);
    String listStudentsAlphabetically(String courseId);
    String searchStudent(String reference);
}
