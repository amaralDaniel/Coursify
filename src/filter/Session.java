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
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@WebFilter("/*")
public class Session implements Filter {

    private final Logger logger = Logger.getLogger(Session.class);

    @EJB
    private AuthEJBRemote authEJB;

    private static final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(new HashSet<String>(
            Arrays.asList("", "/", "/login", "/register")));

    private static final String ASSETS_PATH = "/assets";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.debug(">>>> Session: Filtering Session <<<<");

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        String path = req.getRequestURI().substring(req.getContextPath().length()).replaceAll("[/]+$", "");
        logger.debug(path);

        String sessionToken = Utils.getCookie(req, "sessionToken");
        boolean allowedPath = ALLOWED_PATHS.contains(path) || path.startsWith(ASSETS_PATH);
        logger.debug(allowedPath);

        if(allowedPath|| authEJB.validateSession(sessionToken)) {
            logger.debug("Session authorized");

            filterChain.doFilter(req, res);
        } else {
            logger.debug("Session token doesn't exist");

            res.sendRedirect("logout.jsp");
        }
    }

    @Override
    public void destroy() {

    }
}
