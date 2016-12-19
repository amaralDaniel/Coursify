package servlet;

import org.fluttercode.datafactory.impl.DataFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

@WebServlet("/rest/students")
public class RESTStudents extends HttpServlet {

    protected JSONObject generatedData = null;
    protected int maxRequests = 0;
    protected int currentRequests = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");
        PrintWriter out = resp.getWriter();

        if(generatedData != null && currentRequests < maxRequests) {
            currentRequests++;
            out.print(XML.toString(generatedData));
        } else if(generatedData != null) {
            out.print(XML.toString(generatedData));
        } else {
            currentRequests = 0;
            generatedData = generateResult();
            out.print(generatedData.toString());
        }
    }

    protected JSONObject generateResult() {
        JSONObject result = new JSONObject();


        result.put("result", generateStudents("mail@tomasfrancisco.com"));


        return result;
    }

    protected JSONObject generateCourse(int i) {
        JSONObject jsonObjectCourse = new JSONObject();

        jsonObjectCourse.put("id", i);
        jsonObjectCourse.put("name", "course_" + i);
        jsonObjectCourse.put("description", "description");

        return jsonObjectCourse;
    }

    protected JSONArray generateStudents(String email) {
        JSONObject jsonObjectStudents;
        JSONArray jsonArrayStudents = new JSONArray();

        int numCourses = 3;
        DataFactory df = new DataFactory();


        for(int i = 0; i < 2; i++) {
            jsonObjectStudents = new JSONObject();

            jsonObjectStudents.put("name", "student_" + i);
            jsonObjectStudents.put("email", email != null ? email : df.getEmailAddress());
            jsonObjectStudents.put("birthdate", df.getBirthDate());
            jsonObjectStudents.put("phone", df.getNumberText(9));

            JSONArray jsonArrayCourses = new JSONArray();
            for(int j = 0; j < numCourses; j++) {
                jsonArrayCourses.put(generateCourse(j));
            }

            jsonObjectStudents.put("courses", jsonArrayCourses);
            jsonArrayStudents.put(jsonObjectStudents);
        }

        return jsonArrayStudents;
    }
}
