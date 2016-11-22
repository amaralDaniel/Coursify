package ejb;

import data.Material;
import dto.CourseDTO;
import dto.MaterialDTO;

import javax.ejb.Remote;
import java.util.ArrayList;
import java.util.List;

@Remote
public interface MaterialEJBRemote {
    boolean createMaterial(String filename, String courseId, String userId, String materialType);
    String readMaterial(String materialId);
    boolean updateMaterial(MaterialDTO material);
    boolean deleteMaterial(String materialId);
    ArrayList<MaterialDTO> getMaterials(String courseId);
    MaterialDTO getMaterial(String materialId);
}
