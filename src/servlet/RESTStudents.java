package servlet;

import dto.CourseDTO;
import dto.UserDTO;
import ejb.CourseEJBRemote;
import ejb.StudentEJBRemote;
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

@WebServlet("/rest/students")
public class RESTStudents extends HttpServlet {

//    static final Logger logger = LogManager.getLogger(Courses.class);

    @EJB
    private StudentEJBRemote studentEJB;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");
        PrintWriter out = resp.getWriter();

        ArrayList<UserDTO> students = studentEJB.getStudents();
        JSONArray jsonCourses = new JSONArray();

        for(UserDTO student : students) {
            JSONObject studentObj = new JSONObject();

            studentObj.put("id", student.getUserId());
            studentObj.put("name", student.getName());
            studentObj.put("email", student.getInstitutionalEmail());

            jsonCourses.put(studentObj);
        }

        out.print(jsonCourses.toString());
    }
}
