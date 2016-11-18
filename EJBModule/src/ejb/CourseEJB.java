package ejb;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by danielamaral on 18/11/2016.
 */
public class CourseEJB implements CourseEJBRemote {
    @PersistenceContext(name="Coursify")
    EntityManager em;
}
