package filter;


import ejb.AuthEJBRemote;
import org.apache.log4j.Logger;
import utils.Utils;
import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class Session implements Filter {

    private final Logger logger = Logger.getLogger(Session.class);

    @EJB
    private AuthEJBRemote authEJB;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.debug(">>>> Session: Filtering Session <<<<");

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        String sessionToken = Utils.getCookie(req, "sessionToken");

        if(authEJB.validateSession(sessionToken)) {
            logger.debug("Session token validated successfully");

            filterChain.doFilter(req, res);
        } else {
            logger.debug("Session token doesn't exist");

            res.sendRedirect(req.getContextPath() + "/");
        }
    }

    @Override
    public void destroy() {

    }
}
