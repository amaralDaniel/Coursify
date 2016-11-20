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

    static final Logger myLogger = LogManager.getLogger(AuthEJB.class);

    /**
     * Default constructor. 
     */
    public AuthEJB() {

    }



    //TODO check if it's done properly
    public boolean loginWithCredentials(String email, String password){

        myLogger.info(">>>> Login with Credentials <<<<");

        try{
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] passwordHash = messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));
            myLogger.debug("AuthEJB: created password hash");
            /*Query to check if password hash belongs to the given email*/
            Query newQuery = entityManager.createQuery("FROM User user where user.institutionalEmail=?1 and user.passwordHash=?2");
            newQuery.setParameter(1,email);
            newQuery.setParameter(2,passwordHash);

            User userToLogIn = (User) newQuery.getSingleResult();
            myLogger.debug("AuthEJB: completed query");
            Token newToken = new Token();
            myLogger.debug("AuthEJB: got a token");
            //TODO token stuff
            myLogger.debug("AuthEJB: set token to user");

            myLogger.info("AuthEJB: user "+email+" logged in successfully");
            return true;
        }catch (Exception e){
            myLogger.error("Error while doing login. Credentials may be wrong or don't exist");
            return false;
        }
    }

    /*
    Used to check if given token is in the Token table, thus valid.
     */
    //TODO Test verifyToken
    public boolean verifyToken(Token providedToken){
        myLogger.info(">>>> Verifying Token <<<<");
        Query newQuery = entityManager.createQuery("FROM Token token where token.code=?1");
        newQuery.setParameter(1,providedToken.getCode());

        try{
            newQuery.getSingleResult();
            myLogger.info("AuthEJB: the provided token is valid");
            return true;
        }catch (Exception e){
            myLogger.error("AuthEJB: the provided token is not valid");
            return false;
        }
    }
    //TODO Refactor loginWithToken
    public boolean loginWithToken(String providedToken){
        myLogger.info(">>>> Login with Token <<<<");
        /*HashMap result = new HashMap<>();

        if(verifyToken(providedToken)){
            myLogger.info("AuthEJB: Able to login with the provided token");
            return true;
        }else{
            myLogger.info("AuthEJB: Unable to login with the provided token");
            return false;
        }*/
        return true;
    }


    public boolean createProfessorAccount(String name, String institutionalEmail, String password) {
        myLogger.debug(">>>> AuthEJB: Creating new Professor<<<<");
        Professor prof = null;

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));

            prof = new Professor(name,institutionalEmail,hash);
            entityManager.persist(prof);

            myLogger.info("AuthEJB: New user " + prof.getName() + " created");
            return true;
        }catch(NoSuchAlgorithmException nSAEx){
            myLogger.error("AuthEJB: Couldn't create professor "+prof.getName()+" account");
        }catch (Exception ex){
            myLogger.error("AuthEJB: Couldn't create professor "+prof.getName()+" account");
        }
        return false;
    }

    public boolean createStudentAccount(String name, String institutionalEmail, String password) {
        myLogger.debug(">>>> AuthEJB: Creating new Student<<<<");
        Student student = null;

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));

            student = new Student(name,institutionalEmail,hash);
            entityManager.persist(student);

            myLogger.info("AuthEJB: New user " + student.getName() + " created");
            return true;
        }catch(NoSuchAlgorithmException nSAEx){
            myLogger.error("AuthEJB: Couldn't create student "+student.getName()+" account");
        }catch (Exception ex){
            myLogger.error("AuthEJB: Couldn't create student "+student.getName()+" account");
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
        myLogger.debug("AuthBean: editing account info");

        try {
            entityManager.persist(userToUpdate);
            myLogger.info("AuthBean: Admin edited account successfully");
            return true;
        }catch(Exception ex){
            myLogger.info("AuthBean: Admin failed to edit account");
        }

        return false;
    }

    //TODO removeAccount
    public boolean removeAccount(){return false;}

    public String dummyMethod() {
        return "Hello World!";
    }


}
