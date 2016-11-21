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

@WebServlet("/user/*")
public class User extends HttpServlet {

    @EJB
    private AuthEJBRemote authEJB;

    static final Logger logger = LogManager.getLogger(User.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] pathInfo = req.getPathInfo().split("/");
        String id = pathInfo[1];
        logger.debug("REQUESTED ID: " + id);
        System.out.println("REQUESTED ID: " + id);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String userType = (String) req.getParameter("userType");
        String name = (String) req.getParameter("name");
        String birthdate = (String) req.getParameter("birthdate");
        String institutionalEmail = (String) req.getParameter("institutional-email");
        String alternativeEmail = (String) req.getParameter("alternative-email");
        String address = (String) req.getParameter("address");
        String phone = (String) req.getParameter("phone");
        String category = (String) req.getParameter("category");
        String office = (String) req.getParameter("office");
        String internalPhone = (String) req.getParameter("internal-phone");
        Double salary = Double.valueOf(req.getParameter("salary"));
        String year = (String) req.getParameter("year");

        String password = (String) req.getParameter("password");

        if(userType.equals("PROFESSOR")) {
            authEJB.createProfessorAccount(name, birthdate, institutionalEmail, alternativeEmail,
                    address, phone, category, office, internalPhone, salary, password);
        } else if(userType.equals("STUDENT")) {
            authEJB.createStudentAccount(name, institutionalEmail, password);
        } else if(userType.equals("ADMINISTRATOR")) {
            authEJB.createAdministratorAccount(name, institutionalEmail, password);
        }

        resp.sendRedirect(req.getContextPath() + "/");
    }
}
