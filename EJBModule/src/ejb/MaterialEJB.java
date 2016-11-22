package ejb;

import com.fasterxml.jackson.databind.ObjectMapper;
import data.Administrator;
import data.Course;
import data.Material;
import data.Professor;
import dto.CourseDTO;
import dto.MaterialDTO;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless(name="MaterialEJB")
public class MaterialEJB implements MaterialEJBRemote{
    @PersistenceContext(name="Coursify")
    EntityManager entityManager;

    @EJB
    ProfessorEJBRemote professorEJB;
    @EJB
    CourseEJBRemote courseEJB;


    private final Logger logger = Logger.getLogger(CourseEJB.class);
    static final ObjectMapper mapper = new ObjectMapper();

    //TODO check if createMaterial works
    public boolean createMaterial(String filename, String courseId, String userId, String materialType) {
        logger.debug(">>>> MaterialEJB: Creating course <<<<");

        try {
            Professor professor = professorEJB.getProfessor(userId);
            Course courseToAssign = courseEJB.getCourseEntity(courseId);


            if (professor != null && courseToAssign != null) {
                Material newMaterial = new Material(filename, professor, materialType, courseToAssign);
                entityManager.persist(newMaterial);
                logger.info("New material created");
                return true;
            }
        }catch(Exception ex) {
            logger.info("Something went wrong when trying to create new material. Exception: " + ex.getMessage());
        }
        return false;
    }

    //TODO check if readMaterial works properly
    public String readMaterial(String materialId) {
        logger.debug(">>>> MaterialEJB: Reading material <<<<");

        try {
            Material materialToOutput = getMaterialEntity(materialId);

            logger.debug("AuthEJB: read material");
            return mapper.writeValueAsString(materialToOutput);
        }catch (Exception ex) {
            logger.info("Something went wrong when trying to read a material. Exception: " + ex.getMessage());
        }

        return null;
    }

    //TODO check if updateMaterial works
    public boolean updateMaterial(MaterialDTO material) {
        logger.debug(">>>> MaterialEJB: Updating material <<<<");

        try{
            Material materialEntity = entityManager.find(Material.class, material.getMaterialId());

            materialEntity.setFilename(material.getFilename());

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
            Material materialToRemove = getMaterialEntity(materialId);
            entityManager.remove(materialToRemove);

            logger.debug("Material found and removed");
            return true;
        }catch (Exception ex){
            logger.info("Something went wrong when trying to delete a material. Exception: " + ex.getMessage());
        }
        return false;
    }

    public Material getMaterialEntity (String materialId) {

        try {
            Material materialToOutput = entityManager.find(Material.class, materialId);

            return materialToOutput;
        } catch (Exception e) {
            logger.error("CourseEJB: Error getting courses: " + e.getMessage());
        }
        return null;
    }

    public MaterialDTO getMaterial(String materialId) {

        try {
            Material materialToOutput = entityManager.find(Material.class, materialId);

            return getCourseDTOFromEntity(materialToOutput);
        } catch (Exception e) {
            logger.error("CourseEJB: Error getting courses: " + e.getMessage());
        }
        return null;
    }

    public ArrayList<MaterialDTO> getMaterials(String courseId) {
        try {
            Query query = entityManager.createQuery("FROM Material material WHERE material.course.courseId = ?1");
            query.setParameter(1, courseId);

            List<Material> materials = query.getResultList();

            ArrayList<MaterialDTO> materialsDTO = new ArrayList<MaterialDTO>();

            for(Material material : materials) {
                materialsDTO.add(getCourseDTOFromEntity(material));
            }

            return materialsDTO;
        } catch (Exception e) {
            logger.error("MaterialEJB: Error getting materials: " + e.getMessage());
        }
        return null;
    }

    private MaterialDTO getCourseDTOFromEntity(Material material) throws Exception {
        MaterialDTO materialDTO = new MaterialDTO();

        materialDTO.setMaterialId(material.getMaterialId());
        materialDTO.setFilename(material.getFilename());
        materialDTO.setProfessorName(material.getAuthor().getName());

        return materialDTO;
    }
}

