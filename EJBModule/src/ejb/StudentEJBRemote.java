package ejb;

import data.Student;

import javax.ejb.Remote;

@Remote
public interface StudentEJBRemote {

    Student getStudent(int studentId);
    String listStudentsAlphabetically(int courseId);
}
