package servlet;

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
    private CourseEJBRemote courseEJB;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);

        String sessionToken = Utils.getCookie(req, "sessionToken");

        String courses = courseEJB.getCourses(sessionToken);
        req.setAttribute("courses", courses);
        req.getRequestDispatcher("/home.jsp").forward(req, resp);
    }
}
