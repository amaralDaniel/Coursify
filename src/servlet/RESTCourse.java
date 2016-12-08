package servlet;

import dto.CourseDTO;
import ejb.CourseEJBRemote;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/rest/courses")
public class RESTCourse extends HttpServlet {

//    static final Logger logger = LogManager.getLogger(Courses.class);

    @EJB
    private CourseEJBRemote courseEJB;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");
        PrintWriter out = resp.getWriter();

        ArrayList<CourseDTO> courses = courseEJB.getCourses();
        JSONArray jsonCourses = new JSONArray();

        for(CourseDTO course : courses) {
            JSONObject courseObj = new JSONObject();

            courseObj.put("id", course.getCourseId());
            courseObj.put("name", course.getName());

            jsonCourses.put(courseObj);
        }

        out.print(jsonCourses.toString());
    }
}
