package ejb;

import com.fasterxml.jackson.databind.ObjectMapper;
import data.Course;
import data.Material;
import data.MaterialType;
import data.Professor;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public class MaterialEJB implements MaterialEJBRemote{
    @PersistenceContext(name="Coursify")
    EntityManager entityManager;
    ProfessorEJBRemote professorEJB;
    CourseEJBRemote courseEJB;


    private final Logger logger = Logger.getLogger(CourseEJB.class);
    static final ObjectMapper mapper = new ObjectMapper();

    //TODO check if createMaterial works
    public boolean createMaterial(String filename, String userId, MaterialType materialType, String courseId) {
        logger.debug(">>>> MaterialEJB: Creating course <<<<");

        try {
            Professor professor = professorEJB.getProfessor(userId);
            Course courseToAssign = courseEJB.getCourse(courseId);


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
    public String readMaterial(int materialId) {
        logger.debug(">>>> MaterialEJB: Reading material <<<<");

        try {
            Material materialToOutput = getMaterial(materialId);

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
    public boolean deleteMaterial(int materialId) {
        logger.debug(">>>> MaterialEJB: Deleting material <<<<");

        try{
            Material materialToRemove = getMaterial(materialId);
            entityManager.remove(materialToRemove);

            logger.debug("Material found and removed");
            return true;
        }catch (Exception ex){
            logger.info("Something went wrong when trying to delete a material. Exception: " + ex.getMessage());
        }
        return false;
    }

    public Material getMaterial (int materialId) {

        try {
            Material materialToOutput = entityManager.find(Material.class, materialId);

            return materialToOutput;
        } catch (Exception e) {
            logger.error("CourseEJB: Error getting courses: " + e.getMessage());
        }
        return null;
    }



}

