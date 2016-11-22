package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.CourseDTO;
import ejb.AuthEJBRemote;
import ejb.CourseEJBRemote;
import ejb.UserEJBRemote;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
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
    @EJB
    private UserEJBRemote userEJB;

    static final Logger logger = LogManager.getLogger(Courses.class);
    static final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sessionToken = Utils.getCookie(req, "sessionToken");

        String userType = userEJB.getUserType(sessionToken);
        req.setAttribute("userType", userType);

        CourseDTO[] courses = mapper.readValue(courseEJB.getCourses(sessionToken), CourseDTO[].class);
        req.setAttribute("courses", courses);

        logger.debug("COURSES: " + courses.toString());

        req.getRequestDispatcher("/courses.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sessionToken = Utils.getCookie(req, "sessionToken");

        String name         = (String) req.getParameter("name");
        String description  = (String) req.getParameter("description");
        String professorId  = (String) req.getParameter("professorId");


        courseEJB.createCourse(name, description, professorId);

        resp.sendRedirect(req.getContextPath() + "/dashboard.jsp");
    }
}
