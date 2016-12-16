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
            Arrays.asList("", "/", "/login", "/logout", "/signup", "/register", "/rest/courses", "/rest/students", "/rest/materials")));

    private static final String ASSETS_PATH = "/assets";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.debug(">>>> Session: Filtering Session <<<<");

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        // Enabling CORS
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        res.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        res.setHeader("Access-Control-Expose-Headers", "x-requested-with");

        String path = req.getRequestURI().substring(req.getContextPath().length()).replaceAll("[/]+$", "");
        logger.debug(path);

        String sessionToken = Utils.getCookie(req, "sessionToken");
        boolean allowedPath = ALLOWED_PATHS.contains(path) || path.startsWith(ASSETS_PATH);
        logger.debug("PATH: " + path);

        if(path.equals("/") || path.equals("")) {
            if(authEJB.validateSession(sessionToken)) {
                logger.debug(">>>> Session: Validated session <<<<");
                res.sendRedirect(req.getContextPath() + "/dashboard.jsp");
            } else if(allowedPath) {
                logger.debug(">>>> Session: Allowed Path <<<<");
                filterChain.doFilter(req, res);
            } else {
                logger.debug(">>>> Session: Logging out <<<<");
                res.sendRedirect(req.getContextPath() + "/logout");
            }
        } else {
            if(authEJB.validateSession(sessionToken) || allowedPath) {
                logger.debug(">>>> Session: Validated Session Or Allowed Path <<<<");
                filterChain.doFilter(req, res);
            } else {
                logger.debug(">>>> Session: Logging out <<<<");
                res.sendRedirect(req.getContextPath() + "/logout");
            }
        }
    }

    @Override
    public void destroy() {

    }
}
