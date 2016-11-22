package ejb;

import data.Course;
import dto.CourseDTO;

import javax.ejb.Remote;

@Remote
public interface CourseEJBRemote {
    boolean createCourse(String name, String description, String professorId);
    boolean addStudentToCourse (String courseId, String studentId);
    String readCourse(String courseId);
    boolean updateCourse(CourseDTO course);
    boolean deleteCourse(String courseId);
    String getCourses(String sessionToken);
    CourseDTO getCourse(String courseId);
    Course getCourseEntity(String courseId);
}
