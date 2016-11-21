package servlet;

import ejb.AuthEJBRemote;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signup")
public class Signup extends HttpServlet {

    @EJB
    private AuthEJBRemote authEJB;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = (String) req.getParameter("name");
        String email = (String) req.getParameter("email");
        String password = (String) req.getParameter("password");
        String userType = (String) req.getParameter("userType");

//        if(userType.equals("PROFESSOR")) {
//            authEJB.createProfessorAccount(name, email, password);
//        } else if(userType.equals("STUDENT")) {
//            authEJB.createStudentAccount(name, email, password);
//        } else if(userType.equals("ADMINISTRATOR")) {
//            authEJB.createAdministratorAccount(name, email, password);
//        }

        resp.sendRedirect(req.getContextPath() + "/");
    }
}
