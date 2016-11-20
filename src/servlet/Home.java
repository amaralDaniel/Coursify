package servlet;

import ejb.AuthEJBRemote;
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

@WebServlet("/home")
public class Home extends HttpServlet {

    @EJB
    private AuthEJBRemote authEJB;
    static final Logger logger = LogManager.getLogger(Home.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);

        String sessionToken = Utils.getCookie(req, "sessionToken");
        logger.debug("SESSION TOKEN: " + sessionToken);

        String user = authEJB.readAccount(sessionToken);

        logger.debug("USER: " + user);
    }
}
