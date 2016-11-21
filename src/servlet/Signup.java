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
        super.doPost(req, resp);

        String name     = (String) req.getParameter("name");
        String email    = (String) req.getParameter("email");
        String password = (String) req.getParameter("password");
        String role = (String) req.getParameter("user-role");

//        if(role.equals("student")){
//            authEJB.createStudentAccount(name, email, password);
//        }else{
//            authEJB.createProfessorAccount(name, email, password);
//        }

        authEJB.createProfessorAccount(name, email, password);



        req.getRequestDispatcher("/home.jsp").forward(req, resp);
    }
}