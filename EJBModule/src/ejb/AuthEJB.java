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
import java.util.List;

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

    private User getUserByInstitutionalEmail(String institutionalEmail) {
        logger.info(">>>> Getting User By Institutional Email <<<<");

        try {
            Query newQuery = entityManager.createQuery("FROM User user where user.institutionalEmail=?1");
            newQuery.setParameter(1, institutionalEmail.toString());
            User userToRetrieve = (User) newQuery.getSingleResult();

            logger.info("UserToRetrieve: " + userToRetrieve);
            return userToRetrieve;
        } catch (Exception e) {
            logger.error("AuthEJB: Error getting user by institutional email: " + e.getMessage());
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

    private boolean deleteTokensFromSpecificUser(User user){
        logger.debug(">>>> Deleting Tokens From Specific User <<<<");

        try{
            Query newQuery = entityManager.createQuery("FROM Token token where token.user=?1");
            newQuery.setParameter(1, user);
            List<Token> tokenList = newQuery.getResultList();
            logger.debug("The token list: " + tokenList);

            tokenList.forEach(entityManager::remove);
            logger.info("Removed all tokens from specific user");

            return true;
        } catch(Exception ex){
            logger.error("Something went wrong: Exception: " + ex.getMessage());
        }
        return false;
    }

    public boolean removeAccount(String institutionalEmail) {
        logger.debug(">>>> AuthEJB: Remove user <<<<");

        try{
            User userToRemove= getUserByInstitutionalEmail(institutionalEmail);
            deleteTokensFromSpecificUser(userToRemove);

            entityManager.remove(userToRemove);

            logger.debug("User found and removed");
            return true;
        }catch (Exception ex){
            logger.info("Something went wrong when trying to delete an user. Exception: " + ex.getMessage());
        }
        return false;
    }

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
