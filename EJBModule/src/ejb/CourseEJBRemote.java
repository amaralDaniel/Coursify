package ejb;

import data.Professor;

import javax.ejb.Remote;

@Remote
public interface CourseEJBRemote {
    boolean createCourse(String name, String description, Integer professorId);
    String getCourses(String sessionToken);
    Professor getProfessor(int professorId);

}
