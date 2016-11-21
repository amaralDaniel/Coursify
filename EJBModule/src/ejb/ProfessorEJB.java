package ejb;

import data.Professor;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless(name="ProfessorEJB")
public class ProfessorEJB implements ProfessorEJBRemote{
    @PersistenceContext(name="Coursify")
    EntityManager entityManager;

    private final Logger logger = Logger.getLogger(CourseEJB.class);

    public Professor getProfessor(String professorId) {
        logger.debug(">>>> ProfessorEJB: Fetching Professor <<<<");
        try {
            Professor professor = entityManager.find(Professor.class, professorId);

            return professor;
        } catch (Exception e) {
            logger.error("ProfessorEJB: Error getting professor: " + e.getMessage());
        }
        return null;
    }

}
