package ejb;

import data.Token;
import data.User;
import org.apache.log4j.*;

//logger imports
import javax.ejb.Stateful;
import javax.persistence.*;
import java.util.HashMap;

/**
 * Session Bean implementation class AuthEJB
 */
@Stateful(name="AuthEJB")
public class AuthEJB implements AuthEJBRemote {
    @PersistenceContext(name="Coursify")
    private EntityManager entityManager;


    private HashMap output;

    static Logger myLogger = Logger.getLogger(AuthEJB.class);

    /**
     * Default constructor. 
     */
    public AuthEJB() {
        //Logging work
        BasicConfigurator.configure();
        myLogger.info(">>>> Creating AuthEJB <<<<");

        /*this.entityManagerFactory = Persistence.createEntityManagerFactory("Coursify");
        this.entityManager = entityManagerFactory.createEntityManager();
        this.entityTransaction = entityManager.getTransaction();*/
    }

    //TODO check if it's done properly
    public String loginWithCredentials(String email, String passwordHash){

        myLogger.info(">>>> Login with Credentials <<<<");
        HashMap tempOutput = new HashMap<>();

        /*Query to check if password hash belongs to the given email*/
        Query newQuery = entityManager.createQuery("FROM User user where user.institutionalEmail=?1 and user.passwordHash=?2");
        newQuery.setParameter(1,email);
        newQuery.setParameter(2,passwordHash);

        try{
            User userToLogIn = (User) newQuery.getSingleResult();
            Token newToken = new Token();

            //this.entityTransaction.begin();
            userToLogIn.getToken().setCode(newToken.getCode());
            //this.entityTransaction.commit();

            tempOutput.put("Authentication-Token",newToken);
            this.output = tempOutput;

            myLogger.info("AuthEJB: user "+email+" logged in successfully");
            return "SUCCESS";
        }catch (Exception e){
            myLogger.error("Error while doing login. Credentials may be wrong or don't exist");
            return "ERROR";
        }

    }

    public String dummyMethod() {
        return "Hello World!";
    }
}
