package servlet;

import ejb.AuthEJBRemote;
import ejb.CourseEJBRemote;
import utils.Utils;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/courses")
public class Courses extends HttpServlet {

    @EJB
    private AuthEJBRemote authEJB;
    @EJB
    private CourseEJBRemote courseEJB;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);

        String sessionToken = Utils.getCookie(req, "sessionToken");

        String userType = authEJB.getUserType(sessionToken);
        req.setAttribute("userType", userType);

        String courses = courseEJB.getCourses(sessionToken);
        req.setAttribute("courses", courses);

        req.getRequestDispatcher("/courses.jsp").forward(req, resp);
    }
}