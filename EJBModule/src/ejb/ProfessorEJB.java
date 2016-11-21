package ejb;

import com.fasterxml.jackson.databind.ObjectMapper;
import data.Professor;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by danielamaral on 21/11/2016.
 */
public class ProfessorEJB {
    @PersistenceContext(name="Coursify")
    EntityManager entityManager;

    private final Logger logger = Logger.getLogger(CourseEJB.class);
    static final ObjectMapper mapper = new ObjectMapper();

    public Professor getProfessor(int professorId) {
        logger.debug(">>>> ProfessorEJB: Fetching Professor <<<<");
        try {
            Professor professor = entityManager.find(Professor.class, professorId);

            return professor;
        } catch (Exception e) {
            logger.error("ProfessorEJB: Error getting professor");
        }
        return null;
    }

}
