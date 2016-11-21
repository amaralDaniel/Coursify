package ejb;

import data.Student;

/**
 * Created by danielamaral on 21/11/2016.
 */
public interface StudentEJBRemote {

    Student getStudent(int studentId);

}
