package servlet;

import ejb.UserEJBRemote;
import utils.Utils;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/usertype")
public class UserType extends HttpServlet {

    @EJB
    UserEJBRemote userEJB;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);

        String sessionToken = Utils.getCookie(req, "sessionToken");

        userEJB.getUserType(sessionToken);


    }
}
