package servlet;

import ejb.UserEJB;
import ejb.UserEJBRemote;
import utils.Utils;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("users")
public class Users extends HttpServlet {

    @EJB
    UserEJBRemote userEJB;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sessionToken = Utils.getCookie(req, "sessionToken");

        String userType = userEJB.getUserType(sessionToken);
        req.setAttribute("userType", userType);

        if(userType.equals("ADMINISTRATOR")) {
            req.setAttribute("users", userEJB.getAllUsers(sessionToken));
            req.getRequestDispatcher("/users.jsp").forward(req, resp);
        } else if(userType.equals("PROFESSOR")) {
            userEJB.getStudents(sessionToken);
        } else if(userType.equals("STUDENT")) {
            userEJB.getProfessors(sessionToken);
        }
    }
}
