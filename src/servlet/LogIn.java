package servlet;

import ejb.AuthEJBRemote;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/login")
public class LogIn extends HttpServlet {

    @EJB
    private AuthEJBRemote authEJB;
    static final Logger myLogger = LogManager.getLogger(LogIn.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        myLogger.debug("LogInServlet: get parameters");
        super.doPost(req, resp);

        String email    = (String) req.getParameter("email");
        String password = (String) req.getParameter("password");


        authEJB.loginWithCredentials(email, password);
        myLogger.debug("LogInServlet: called loginWithCredentials method");
        req.getRequestDispatcher("/home.jsp").forward(req, resp);
    }
}
