package ejb;

import com.fasterxml.jackson.databind.ObjectMapper;
import data.Professor;
import data.Student;
import data.Token;
import data.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Stateless(name="AuthEJB")
public class AuthEJB implements AuthEJBRemote {
    @PersistenceContext(name="Coursify")
    private EntityManager entityManager;

    static final Logger logger = LogManager.getLogger(AuthEJB.class);
    static final ObjectMapper mapper = new ObjectMapper();

    public AuthEJB() {

    }

    public String getUserSessionToken(String email, String password) {
        logger.info(">>>> Login with Credentials <<<<");

        try {
            User user = getUserByCredentials(email, password);

            if(user != null) {
                Token token = createNewSessionToken(user);

                return token.getSessionToken();
            }

            return null;
        } catch (Exception e) {
            logger.error("AuthEJB: Error getting User Session Token");
        }
        return null;
    }

    private Token createNewSessionToken(User user) {
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

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] passwordHash = messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));
            logger.debug("AuthEJB: created password hash");


            Query newQuery = entityManager.createQuery("FROM User user where user.institutionalEmail=?1 and user.passwordHash=?2");
            newQuery.setParameter(1, email);
            newQuery.setParameter(2, passwordHash);
            User userToLogIn = (User) newQuery.getSingleResult();
            logger.debug("AuthEJB: completed query");

            return userToLogIn;
        } catch (Exception e) {
            logger.error("AuthEJB: Error getting User by credentials: " + e.getMessage());
        }

        return null;
    }

    public boolean validateSession(String sessionToken) {
        logger.debug(">>>> AuthEJB: Validating session <<<<");

        try {
            Token result = entityManager.find(Token.class, sessionToken);
            return result != null ? true : false;
        } catch (Exception e) {
            logger.info("Session token invalid");
            return false;
        }
    }

    public boolean createProfessorAccount(String name, String institutionalEmail, String password) {
        logger.debug(">>>> AuthEJB: Creating new Professor<<<<");
        Professor prof = null;

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));

            prof = new Professor(name, institutionalEmail, hash);
            entityManager.persist(prof);

            logger.info("AuthEJB: New user " + prof.getName() + " created");
            return true;
        } catch(NoSuchAlgorithmException nSAEx){
            logger.error("AuthEJB: Couldn't create professor "+prof.getName()+" account");
        } catch (Exception ex){
            logger.error("AuthEJB: Couldn't create professor "+prof.getName()+" account");
        }
        return false;
    }

    public boolean createStudentAccount(String name, String institutionalEmail, String password) {
        logger.debug(">>>> AuthEJB: Creating new Student<<<<");
        Student student = null;

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));

            student = new Student(name,institutionalEmail,hash);
            entityManager.persist(student);

            logger.info("AuthEJB: New user " + student.getName() + " created");
            return true;
        } catch(NoSuchAlgorithmException nSAEx){
            logger.error("AuthEJB: Couldn't create student "+student.getName()+" account");
        } catch (Exception ex){
            logger.error("AuthEJB: Couldn't create student "+student.getName()+" account");
        }
        return false;
    }

    //TODO Test readAccount
    public String readAccount(String sessionToken) {
        logger.debug(">>>> AuthEJB: Read account <<<<");
        try {
            Token token = getSessionToken(sessionToken);

            logger.debug("AuthEJB: getting user from token");
            User user = token.getUser();

            logger.debug("AuthEJB: read user account");
            return mapper.writeValueAsString(user);
        } catch (Exception e) {
            logger.error("AuthEJB: Couldn't read information from User account: " + e.getMessage());
        }
        return null;
    }

    private Token getSessionToken(String sessionToken){
        logger.debug(">>>> AuthEJB: getSessionToken <<<<");
        try {
            Token token = entityManager.find(Token.class, sessionToken);

            return token;
        }catch(Exception ex){
            logger.error("AuthEJB: Couldn't read information from given session token");
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

        String userType = getUserType(sessionToken);

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

    public String getUserType(String sessionToken) {
        logger.debug(">>>> AuthEJB: Getting user type <<<<");

        Token token = getSessionToken(sessionToken);

        return token.getUser().getClass().toString().toUpperCase();
    }
}
