package ejb;

import data.Course;

import javax.ejb.Remote;

@Remote
public interface CourseEJBRemote {
    boolean createCourse(String name, String description, Integer professorId);
    boolean addStudentToCourse (int courseId, int studentId);
    String readCourse(int courseId);
    boolean updateCourse(String materialObjectMapper);
    boolean deleteCourse(int materialId);
    Course getCourse (int courseId);
    String getCourses(String sessionToken);


}
