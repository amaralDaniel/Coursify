package servlet;

import ejb.AuthEJBRemote;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class PlayersTallerThan
 */
// http://localhost:8080/ServerPlayers-Web/PlayersTallerThan?fill=1
// url = http://localhost:8080/ServerPlayers-Web/PlayersTallerThan?height=1.80
@WebServlet("/DummyServlet")
public class DummyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @EJB
    AuthEJBRemote ejbremote;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DummyServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        String output = ejbremote.dummyMethod();
        out.println(output + "<br/>");
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
