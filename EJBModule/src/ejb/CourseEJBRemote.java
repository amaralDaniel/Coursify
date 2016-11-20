package ejb;

import javax.ejb.Remote;

@Remote
public interface CourseEJBRemote {

    String getCourses(String sessionToken);
}
