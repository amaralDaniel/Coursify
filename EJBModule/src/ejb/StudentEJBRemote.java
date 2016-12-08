package ejb;

import data.Student;
import dto.UserDTO;

import javax.ejb.Remote;
import java.util.ArrayList;
import java.util.List;

@Remote
public interface StudentEJBRemote {

    Student getStudent(String studentId);
    String listStudentsAlphabetically(String courseId);
    String searchStudent(String reference);
    ArrayList<UserDTO> getStudents();
}
