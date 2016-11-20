package servlet;

import ejb.AuthEJBRemote;
import utils.Utils;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logout")
public class Logout extends HttpServlet {

    @EJB
    private AuthEJBRemote authEJB;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sessionToken = Utils.getCookie(req, "sessionToken");

        authEJB.logout(sessionToken);

        Cookie sessionTokenCookie = new Cookie("sessionToken", sessionToken);
        sessionTokenCookie.setMaxAge(0);
        resp.addCookie(sessionTokenCookie);

        resp.sendRedirect(req.getContextPath() + "/");
    }
}
