package ejb;

import data.Administrator;
import data.Professor;
import data.Token;
import data.User;
import org.apache.log4j.*;
import org.apache.log4j.Logger;
import org.jboss.logging.*;

//logger imports
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.List;

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

    private final Logger logger = Logger.getLogger(AuthEJB.class);


    /**
     * Default constructor. 
     */
    public AuthEJB() {
        //Logging work
        //BasicConfigurator.conf;
        //myLogger.info(">>>> Creating AuthEJB <<<<");

        //myLogger.setLevel(Level.ERROR);

//        this.entityManagerFactory = Persistence.createEntityManagerFactory("Coursify");
//        this.entityManager = entityManagerFactory.createEntityManager();
//        this.entityTransaction = entityManager.getTransaction();
    }



    //TODO check if it's done properly
    public String loginWithCredentials(String email, String passwordHash){

        //myLogger.info(">>>> Login with Credentials <<<<");
        HashMap tempOutput = new HashMap<>();

        /*Query to check if password hash belongs to the given email*/
        Query newQuery = entityManager.createQuery("FROM User user where user.institutionalEmail=?1 and user.passwordHash=?2");
        newQuery.setParameter(1,email);
        newQuery.setParameter(2,passwordHash);

        try{
            User userToLogIn = (User) newQuery.getSingleResult();
            Token newToken = new Token();

            //this.entityTransaction.begin();
            //userToLogIn.getToken().setCode(newToken.getCode());
            //this.entityTransaction.commit();

            tempOutput.put("Authentication-Token",newToken);
            this.output = tempOutput;

            //myLogger.info("AuthEJB: user "+email+" logged in successfully");
            return "SUCCESS";
        }catch (Exception e){
            //myLogger.error("Error while doing login. Credentials may be wrong or don't exist");
            return "ERROR";
        }
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



    //TODO showAccountInfo
    //TODO editAccountInfo
    //TODO removeAccount


    public String dummyMethod() {
        return "Hello World!";
    }

    public boolean signUp(String name, String email, String password) {
        logger.info("Logger working!");
        System.out.printf("Print");


        Professor admin = entityManager.find(Professor.class, 2);

        logger.info(admin.getName());

//        myLogger.info("AuthEJB: New user created");
        return true;
    }

    public void logout(String sessionToken) {

        Token result = entityManager.find(Token.class, sessionToken);

        if(result != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(result);
            entityManager.getTransaction().commit();
        }
    }
}
