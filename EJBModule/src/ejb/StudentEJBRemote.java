package ejb;

import data.Student;

import javax.ejb.Remote;

@Remote
public interface StudentEJBRemote {

    Student getStudent(String studentId);
    String listStudentsAlphabetically(String courseId);
}
