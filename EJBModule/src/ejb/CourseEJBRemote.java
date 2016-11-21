package ejb;

import javax.ejb.Remote;

@Remote
public interface CourseEJBRemote {
    boolean createCourse(String sessionToken, String name, String description, String professorId);
    String getCourses(String sessionToken);

}
