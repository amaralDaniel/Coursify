package servlet;

import dto.CourseDTO;
import dto.UserDTO;
import ejb.CourseEJBRemote;
import ejb.UserEJBRemote;
import utils.Utils;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/course")
public class Course extends HttpServlet {

    @EJB
    CourseEJBRemote courseEJB;
    @EJB
    UserEJBRemote userEJB;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sessionToken = Utils.getCookie(req, "sessionToken");
        String courseId = req.getParameter("id");
        CourseDTO course = courseEJB.getCourse(courseId);
        String userType = userEJB.getUserType(sessionToken);
        String userId = userEJB.getUserId(sessionToken);

        req.setAttribute("course", course);
        req.setAttribute("userType", userType);
        req.setAttribute("userId", userId);

        req.getRequestDispatcher("/course-response.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String update = req.getParameter("update");
        String delete = req.getParameter("delete");
        String create = req.getParameter("create");

        String courseId = req.getParameter("courseId");
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String professorId = req.getParameter("professorId");

        if(create != null && create.equals("true")) {
            courseEJB.createCourse(name, description, professorId);
        } else if(update != null && update.equals("true")) {
            courseEJB.updateCourse(createCourseDTO(courseId, name, description));
        } else if(delete != null && delete.equals("true")) {
            courseEJB.deleteCourse(courseId);
        }
        resp.sendRedirect(req.getContextPath() + "/");
        return;
    }

    private CourseDTO createCourseDTO(String courseId, String name, String description) {
        CourseDTO courseDTO = new CourseDTO();

        courseDTO.setCourseId(courseId);
        courseDTO.setName(name);
        courseDTO.setDescription(description);

        return courseDTO;
    }
}
