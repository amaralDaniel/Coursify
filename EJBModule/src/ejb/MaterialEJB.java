package ejb;

import com.fasterxml.jackson.databind.ObjectMapper;
import data.Course;
import data.MaterialType;
import data.Professor;
import data.Material;
import org.apache.log4j.Logger;
import org.hibernate.mapping.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


public class MaterialEJB implements MaterialEJBRemote{
    @PersistenceContext(name="Coursify")
    EntityManager entityManager;

    private final Logger logger = Logger.getLogger(CourseEJB.class);
    static final ObjectMapper mapper = new ObjectMapper();

    //TODO check if createMaterial works
    public boolean createMaterial(String filename, int userId, MaterialType materialType, String courseId) {
        logger.debug(">>>> MaterialEJB: Creating course <<<<");

        try {
            Professor professor = entityManager.find(Professor.class, userId);
            Course courseToAssign = entityManager.find(Course.class, courseId);


            if (professor != null && courseToAssign != null) {
                Material newMaterial = new Material(filename, professor, materialType, courseToAssign);
                entityManager.persist(newMaterial);
                logger.info("New material created");
                return true;
            }
        }catch(Exception ex) {
            logger.info("Something went wrong when trying to create new material created. Exception: " + ex.getMessage());
        }
        return false;
    }

    //TODO check if readMaterial works properly
    public String readMaterial(String materialId) {
        logger.debug(">>>> MaterialEJB: Reading material <<<<");

        try {
            Material materialToOutput = entityManager.find(Material.class, materialId);

            logger.debug("AuthEJB: read material");
            return mapper.writeValueAsString(materialToOutput);
        }catch (Exception ex){
            logger.info("Something went wrong when trying to read a material. Exception: " + ex.getMessage());
        }

        return null;
    }

    //TODO check if updateMaterial works
    public boolean updateMaterial(String materialObjectMapper) {
        logger.debug(">>>> MaterialEJB: Updating material <<<<");

        try{
            Material materialToUpdate = mapper.readValue(materialObjectMapper,Material.class);
            entityManager.persist(materialToUpdate);
            return true;
        }catch (Exception ex){
            logger.info("Something went wrong when trying to update a material. Exception: " + ex.getMessage());
        }

        return false;
    }

    //TODO check if deleteMaterial works
    public boolean deleteMaterial(String materialId) {
        logger.debug(">>>> MaterialEJB: Deleting material <<<<");

        try{
            Material materialToRemove = entityManager.find(Material.class, materialId);
            entityManager.remove(materialToRemove);

            logger.debug("Material found and removed");
            return true;
        }catch (Exception ex){
            logger.info("Something went wrong when trying to delete a material. Exception: " + ex.getMessage());
        }
        return false;
    }

    //TODO getMaterial
    public String getMaterial(String sessionToken) {
        //TODO: Limit access to user through session token
        try {
            Query query = entityManager.createQuery("SELECT courses FROM Course courses");
            Collection courses = (Collection) query.getResultList();
            return mapper.writeValueAsString(courses);
        } catch (Exception e) {
            logger.error("CourseEJB: Error getting courses: " + e.getMessage());
        }
        return null;
    }
}

