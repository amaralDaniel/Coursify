package ejb;

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
import java.util.HashMap;

//myLogger imports

/**
 * Session Bean implementation class AuthEJB
 */
@Stateless(name="AuthEJB")
public class AuthEJB implements AuthEJBRemote {
//    private final EntityManagerFactory entityManagerFactory;
//    private final EntityTransaction entityTransaction;
    @PersistenceContext(name="Coursify")
    private EntityManager entityManager;


    private HashMap output;

    static final Logger logger = LogManager.getLogger(AuthEJB.class);

    /**
     * Default constructor. 
     */
    public AuthEJB() {

    }



    //TODO check if it's done properly
    public String loginWithCredentials(String email, String password) {
        logger.info(">>>> Login with Credentials <<<<");

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] passwordHash = messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));
            logger.debug("AuthEJB: created password hash");
            /*Query to check if password hash belongs to the given email*/
            Query newQuery = entityManager.createQuery("FROM User user where user.institutionalEmail=?1 and user.passwordHash=?2");
            newQuery.setParameter(1,email);
            newQuery.setParameter(2,passwordHash);

            User userToLogIn = (User) newQuery.getSingleResult();
            logger.debug("AuthEJB: completed query");
            Token newToken = new Token(userToLogIn);

            logger.debug("AuthEJB: got a token");
            //TODO token stuff
            logger.debug("AuthEJB: set token to user");
            entityManager.persist(newToken);

            logger.info("AuthEJB: user "+email+" logged in successfully");
            return newToken.getSessionToken();
        } catch (Exception e) {
            logger.error("Error while doing login. Credentials may be wrong or don't exist");
            return null;
        }
    }

    /*
    Used to check if given token is in the Token table, thus valid.
     */
    //TODO Test verifyToken
//    public boolean verifyToken(Token providedToken){
//        logger.info(">>>> Verifying Token <<<<");
//        Query newQuery = entityManager.createQuery("FROM Token token where token.code=?1");
//        newQuery.setParameter(1,providedToken.getSessionToken());
//
//        try{
//            newQuery.getSingleResult();
//            logger.info("AuthEJB: the provided token is valid");
//            return true;
//        }catch (Exception e){
//            logger.error("AuthEJB: the provided token is not valid");
//            return false;
//        }
//    }

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
        }catch(NoSuchAlgorithmException nSAEx){
            logger.error("AuthEJB: Couldn't create professor "+prof.getName()+" account");
        }catch (Exception ex){
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
        }catch(NoSuchAlgorithmException nSAEx){
            logger.error("AuthEJB: Couldn't create student "+student.getName()+" account");
        }catch (Exception ex){
            logger.error("AuthEJB: Couldn't create student "+student.getName()+" account");
        }
        return false;
    }

    //TODO Test readAccount
    public boolean readAccount(Token providedToken){
        /*myLogger.debug(">>>> AuthEJB: Read account <<<<");
        User outputUser = null;

        if (!verifyToken(providedToken)) {
            myLogger.error("AuthBean: Error retrieving info of account. Token error.");
            return false;
        }

        try {
            Query to check if password hash belongs to the given email
            Query newQuery = entityManager.find("FROM User user WHERE user.token.code=?1");
            newQuery.setParameter(1,providedToken.getCode());

            outputUser = (User) newQuery.getSingleResult();

            myLogger.info("AuthEJB: New user " + outputUser.getName() + " created");
            return true;
        }catch (Exception ex){
            myLogger.error("AuthEJB: Couldn't read information from User "+outputUser.getName()+" account");
        }*/
        return false;
    }

    //TODO updateAccount
    public boolean updateAcount(User userToUpdate){
        logger.debug("AuthBean: editing account info");

        try {
            entityManager.persist(userToUpdate);
            logger.info("AuthBean: Admin edited account successfully");
            return true;
        }catch(Exception ex){
            logger.info("AuthBean: Admin failed to edit account");
        }

        return false;
    }

    //TODO removeAccount
    public boolean removeAccount(){return false;}

    public String dummyMethod() {
        return "Hello World!";
    }

    public void logout(String sessionToken) {
        logger.debug(">>>> AuthEJB: Logging out <<<<");

        if(sessionToken != null) {
            Token result = entityManager.find(Token.class, sessionToken);

            if (result != null) {
                entityManager.getTransaction().begin();
                entityManager.remove(result);
                entityManager.getTransaction().commit();
            }
        }
    }
}
