package servlet;

import filter.Session;
import org.apache.log4j.Logger;
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

@WebServlet("/rest/materials")
public class RESTMaterials extends HttpServlet {

    private final Logger logger = Logger.getLogger(RESTMaterials.class);

    protected JSONObject generatedData = null;
    protected int maxRequests = 2;
    protected int currentRequests = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/xml");
        resp.setCharacterEncoding("utf-8");
        PrintWriter out = resp.getWriter();

        if(generatedData != null && currentRequests < maxRequests) {
            currentRequests++;
            logger.info(XML.toString(generatedData));
            out.print(XML.toString(generatedData));
        } else {
            currentRequests = 0;
            generatedData = generateResult();
            logger.info(XML.toString(generatedData));
            out.print(XML.toString(generatedData));
        }

        out.close();
    }

    protected JSONObject generateResult() {
        int max = 15;
        int min = 1;
        Random rand = new Random();

        JSONArray result = new JSONArray();

        int randomNum = rand.nextInt((max - min) + 1) + min;
        for(int i = 0; i < 2; i++) {
            result.put(generateCourse(i));
        }

        JSONObject nested = new JSONObject();
        JSONObject resultObj = new JSONObject();
        nested.put("wrapper", result);
        resultObj.put("result", nested);
        logger.info(resultObj.toString());
        return resultObj;
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
