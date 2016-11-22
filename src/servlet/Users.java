package servlet;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.UserDTO;
import ejb.UserEJBRemote;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import utils.Utils;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/users")
public class Users extends HttpServlet {

    @EJB
    UserEJBRemote userEJB;

    static final Logger logger = LogManager.getLogger(Users.class);
    static final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug(">>>> Calling Users Servlet <<<<");
        String sessionToken = Utils.getCookie(req, "sessionToken");

        String userType = userEJB.getUserType(sessionToken);
        req.setAttribute("userType", userType);

        if(userType.equals("ADMINISTRATOR")) {
            UserDTO[] users = mapper.readValue(userEJB.getAllUsers(sessionToken), UserDTO[].class);
            req.setAttribute("users", users);
            req.getRequestDispatcher("/users.jsp").forward(req, resp);
        } else if(userType.equals("PROFESSOR")) {
            UserDTO[] users = mapper.readValue(userEJB.getStudents(sessionToken), UserDTO[].class);
            req.setAttribute("users", users);
            req.getRequestDispatcher("/users.jsp").forward(req, resp);
        } else if(userType.equals("STUDENT")) {
            UserDTO[] users = mapper.readValue(userEJB.getProfessors(sessionToken), UserDTO[].class);
            req.setAttribute("users", users);
            req.getRequestDispatcher("/users.jsp").forward(req, resp);
        }
    }
}
