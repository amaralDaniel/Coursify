package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.UserDTO;
import ejb.AuthEJBRemote;
import ejb.StudentEJBRemote;
import ejb.UserEJBRemote;
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

@WebServlet("/user")
public class User extends HttpServlet {

    @EJB
    private AuthEJBRemote authEJB;

    @EJB
    private UserEJBRemote userEJB;

    @EJB
    private StudentEJBRemote studentEJB;

    static final Logger logger = LogManager.getLogger(User.class);
    static final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sessionToken = Utils.getCookie(req, "sessionToken");

        String search = req.getParameter("search");

        if(search != null && search.equals("true")) {
            String keywords = req.getParameter("keywords");

            String studentId = studentEJB.searchStudent(keywords);

            resp.sendRedirect(req.getContextPath() + "/user.jsp?id="+studentId);
            return;
        }

        String userId = req.getParameter("id");
        UserDTO user = userEJB.getUser(sessionToken, userId);
        String loggedInUserId = userEJB.getUserId(sessionToken);

        req.setAttribute("user", user);
        req.setAttribute("loggedInUserId", loggedInUserId);

        req.getRequestDispatcher("/user-response.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO: limit by session Token

        String update = req.getParameter("update");
        String delete = req.getParameter("delete");
        String create = req.getParameter("create");

        String userId = req.getParameter("userId");
        String sessionToken = Utils.getCookie(req, "sessionToken");

        if(delete != null && delete.equals("true")) {
            userEJB.deleteUser(sessionToken, userId);

            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }

        String userType = req.getParameter("userType");

        String name = req.getParameter("name");
        String birthdate = req.getParameter("birthdate");
        String institutionalEmail = req.getParameter("institutional-email");
        String alternativeEmail = req.getParameter("alternative-email");
        String address = req.getParameter("address");
        String phone = req.getParameter("phone");
        String category = null;
        String office = null;
        String internalPhone = null;
        String salary = null;

        logger.debug("USER TYPE: " + userType);

        if(userType.equals("PROFESSOR")) {
            category = req.getParameter("category");
            office = req.getParameter("office");
            internalPhone = req.getParameter("internal-phone");
            salary = req.getParameter("salary");
            logger.debug("SALARY: " + salary);
        }

        String year = null;
        if(userType.equals("STUDENT")) {
            year = req.getParameter("year");
        }


        String password = req.getParameter("password");

        if(update != null && update.equals("true")) {
            userEJB.updateUser(createUserDTO(userId, userType, name, birthdate, institutionalEmail, alternativeEmail, address,
                                phone, category, office, internalPhone, salary, year));
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }

        if(create != null && create.equals("true")) {
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

    private UserDTO createUserDTO(String userId, String userType, String name, String birthdate, String institutionalEmail,
                                  String alternativeEmail, String address, String phone, String category,
                                  String office, String internalPhone, String salary, String yearRegistry) {
        UserDTO user = new UserDTO();

        user.setUserId(userId);
        user.setUserType(userType);
        user.setName(name);
        user.setBirthdate(birthdate);
        user.setInstitutionalEmail(institutionalEmail);
        user.setEmail(alternativeEmail);
        user.setAddress(address);
        user.setTelephone(phone);
        user.setCategory(category);
        user.setOffice(office);
        user.setInternalTelephone(internalPhone);
        user.setSalary(salary);
        user.setYearRegistry(yearRegistry);

        return user;
    }
}
