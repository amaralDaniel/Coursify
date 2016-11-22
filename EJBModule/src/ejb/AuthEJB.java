package ejb;

import com.fasterxml.jackson.databind.ObjectMapper;
import data.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@Stateless(name="AuthEJB")
public class AuthEJB implements AuthEJBRemote {
    @PersistenceContext(name="Coursify")
    private EntityManager entityManager;

    static final Logger logger = LogManager.getLogger(AuthEJB.class);
    static final ObjectMapper mapper = new ObjectMapper();

    @EJB
    UserEJBRemote userEJB;

    public AuthEJB() {

    }

    public String createUserSessionToken(String email, String password) {
        logger.info(">>>> Creating User Session Token <<<<");

        try {
            User user = getUserByCredentials(email, password);

            if(user != null) {
                Token token = createNewSessionToken(user);

                return token.getSessionToken();
            }

            return null;
        } catch (Exception e) {
            logger.error("AuthEJB: Error creating User Session Token ");
        }
        return null;
    }

    private Token createNewSessionToken(User user) {
        logger.info(">>>> Creating New Session Token <<<<");
        Token newToken = new Token(user);

        try {
            entityManager.persist(newToken);

            return newToken;
        } catch (Exception e) {
            logger.error("AuthEJB: Error creating new session token: " + e.getMessage());
        }

        return null;
    }

    private User getUserByCredentials(String email, String password) {
        logger.info(">>>> Getting User By Credentials <<<<");

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] passwordHash = messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));

            Query newQuery = entityManager.createQuery("FROM User user where user.institutionalEmail=?1 and user.passwordHash=?2");
            newQuery.setParameter(1, email);
            newQuery.setParameter(2, passwordHash);
            User userToLogIn = (User) newQuery.getSingleResult();

            return userToLogIn;
        } catch (Exception e) {
            logger.error("AuthEJB: Error getting user by credentials: " + e.getMessage());
        }

        return null;
    }

    public boolean validateSession(String sessionToken) {
        logger.info(">>>> Validating session <<<<");

        try {
            Token result = entityManager.find(Token.class, sessionToken);
            return result != null ? true : false;
        } catch (Exception e) {
            logger.error("AuthEJB: Error validating session: " + e.getMessage());
            return false;
        }
    }

    public boolean createAdministratorAccount(String name, String institutionalEmail, String password) {
        logger.info(">>>> Creating Administrator Account <<<<");
        Administrator administrator = null;

        try {
            byte[] hash = getHashFromPassword(password);

            administrator = new Administrator(name, institutionalEmail, hash);
            entityManager.persist(administrator);

            logger.debug("AuthEJB: Administrator Account Created");
            return true;
        } catch (Exception e){
            logger.error("AuthEJB: Error creating administrator account: " + e.getMessage());
        }
        return false;
    }

    public boolean createProfessorAccount(String name, String birthdate, String institutionalEmail,
                                          String alternativeEmail, String address, String phone, String category,
                                          String office, String internalPhone, Double salary, String password) {
        logger.info(">>>> Creating Professor Account <<<<");
        Professor prof = null;

        try {
            byte[] hash = getHashFromPassword(password);

            prof = new Professor(name, birthdate, institutionalEmail, alternativeEmail, address, phone, category,
                    office, internalPhone, salary, hash);
            entityManager.persist(prof);

            logger.debug("AuthEJB: Professor Account Created");
            return true;
        } catch (Exception e){
            logger.error("AuthEJB: Error creating professor account: " + e.getMessage());
        }
        return false;
    }

    public boolean createProfessorAccount(String name, String institutionalEmail, String password) {
        logger.info(">>>> Creating Professor Account <<<<");
        Professor prof = null;

        try {
            byte[] hash = getHashFromPassword(password);

            prof = new Professor(name, institutionalEmail, hash);
            entityManager.persist(prof);

            logger.debug("AuthEJB: Professor Account Created");
            return true;
        } catch (Exception e){
            logger.error("AuthEJB: Error creating professor account: " + e.getMessage());
        }
        return false;
    }

    public boolean createStudentAccount(String name, String institutionalEmail, String password) {
        logger.info(">>>> AuthEJB: Creating Student Account <<<<");
        Student student = null;

        try {
            byte[] hash = getHashFromPassword(password);

            student = new Student(name, institutionalEmail, hash);

            entityManager.persist(student);

            return true;
        } catch (Exception e){
            logger.error("AuthEJB: Error creating student account: " + e.getMessage());
        }
        return false;
    }

    private byte[] getHashFromPassword(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));
            return hash;
        } catch (Exception e) {
            logger.error("AuthEJB: Error getting hash from password: " + e.getMessage());
        }
        return null;
    }

    //TODO Test readAccount
    public String readAccount(String sessionToken) {
        logger.info(">>>> AuthEJB: Reading account <<<<");
        try {
            Token token = getSessionToken(sessionToken);

            logger.debug("AuthEJB: getting user from token");
            User user = token.getUser();

            logger.debug("AuthEJB: read user account");
            return mapper.writeValueAsString(user);
        } catch (Exception e) {
            logger.error("AuthEJB: Error reanding account: " + e.getMessage());
        }
        return null;
    }

    public Token getSessionToken(String sessionToken) {
        logger.debug(">>>> AuthEJB: Getting Session Token <<<<");
        try {
            Token token = entityManager.find(Token.class, sessionToken);

            return token;
        } catch(Exception e) {
            logger.error("AuthEJB: Error getting sessio token: " + e.getMessage());
        }
        return null;
    }

    //TODO updateAccount
    public boolean updateAcount(String sessionToken) {
        logger.debug("AuthBean: editing account info");



        try {
            logger.info("AuthBean: Admin edited account successfully");
            return true;
        }catch(Exception ex){
            logger.info("AuthBean: Admin failed to edit account");
        }

        return false;
    }

    public boolean updateAccount(String sessionToken, String userId) {

        String userType = userEJB.getUserType(sessionToken);

        if(userType.equals("ADMINISTRATOR")) {

        }

        return false;
    }

    //TODO removeAccount
    public boolean removeAccount(){return false;}

    public void logout(String sessionToken) {
        logger.debug(">>>> AuthEJB: Logging out <<<<");

        if(sessionToken != null) {
            try {
                Token result = entityManager.find(Token.class, sessionToken);


                if (result != null) {
                    entityManager.getTransaction().begin();
                    entityManager.remove(result);
                    entityManager.getTransaction().commit();
                    logger.debug("AuthEJB: Removed session token");
                }
            } catch (Exception e) {
                logger.error("AuthEJB: " + e.getMessage());
            }
        }
    }
}
