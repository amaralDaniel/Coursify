package servlet;

import org.fluttercode.datafactory.impl.DataFactory;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

@WebServlet("/rest/materials")
public class RESTMaterials extends HttpServlet {

    protected JSONArray generatedData = null;
    protected int maxRequests = 2;
    protected int currentRequests = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");
        PrintWriter out = resp.getWriter();

        if(generatedData != null && currentRequests < maxRequests) {
            currentRequests++;
            out.print(org.json.XML.toString(generatedData));
        } else {
            currentRequests = 0;
            generatedData = generateResult();
            out.print(org.json.XML.toString(generatedData));
        }
    }

    protected JSONArray generateResult() {
        int max = 15;
        int min = 1;
        Random rand = new Random();

        JSONArray result = new JSONArray();

        int randomNum = rand.nextInt((max - min) + 1) + min;
        for(int i = 0; i < 2; i++) {
            result.put(generateCourse(i));
        }

        return result;
    }
//
//    protected JSONObject generateCourse() {
//
//        //Fairy fairy = Fairy.create();
//
//        JSONObject jsonObjectCourse = new JSONObject();
//
//        //Company course = fairy.company();
//        DataFactory df = new DataFactory();
//
//        jsonObjectCourse.put("name", df.getBusinessName());
//        jsonObjectCourse.put("description", df.getRandomWord() + " " + df.getRandomWord() + " " + df.getRandomWord() + " " + df.getRandomWord() + " " + df.getRandomWord());
//        jsonObjectCourse.put("materials", generateMaterials());
//
//        return jsonObjectCourse;
//    }

    protected JSONObject generateCourse(int i) {
        JSONObject jsonObjectCourse = new JSONObject();


        jsonObjectCourse.put("name", "course_" + i);
        jsonObjectCourse.put("description", "description");
        jsonObjectCourse.put("materials", generateMaterials());

        return jsonObjectCourse;
    }

    protected JSONArray generateMaterials() {
        JSONObject jsonObjectMaterials;
        JSONArray jsonArrayMaterials = new JSONArray();
        int max = 15;
        int min = 1;
        Random rand = new Random();
        DataFactory df = new DataFactory();

        //int randomNum = rand.nextInt((max - min) + 1) + min;
        for(int i = 0; i < 2; i++) {
            jsonObjectMaterials = new JSONObject();

            jsonObjectMaterials.put("name", "material_" + i);
            jsonObjectMaterials.put("date", df.getBirthDate());
            jsonArrayMaterials.put(jsonObjectMaterials);
        }

        return jsonArrayMaterials;
    }
}
