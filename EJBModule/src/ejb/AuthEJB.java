package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class PlayersEJB
 */
@Stateless
public class AuthEJB implements AuthEJBRemote {
    @PersistenceContext(name="Coursify")
    EntityManager em;

    /**
     * Default constructor. 
     */
    public AuthEJB() {
        // TODO Auto-generated constructor stub
    }

    public String dummyMethod() {
        return "Hello World!";
    }
}
