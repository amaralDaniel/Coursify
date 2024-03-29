package servlet;

import ejb.AuthEJBRemote;
import utils.Utils;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/login")
public class Login extends HttpServlet {

    @EJB
    private AuthEJBRemote authEJB;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email    = (String) req.getParameter("email");
        String password = (String) req.getParameter("password");

        String sessionToken = authEJB.createUserSessionToken(email, password);

        if(sessionToken != null) {
            Utils.setCookie(resp, "sessionToken", sessionToken);
            resp.sendRedirect(req.getContextPath() + "/dashboard.jsp");
        } else {
            Utils.removeCookie(resp, "sessionToken");
            resp.sendRedirect(req.getContextPath() + "/");
        }
    }
}
