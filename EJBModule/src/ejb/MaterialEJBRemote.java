package ejb;

import data.Material;
import data.MaterialType;

import javax.ejb.Remote;

@Remote
public interface MaterialEJBRemote {
    boolean createMaterial(String filename, String userId, MaterialType materialType, String courseId);
    String readMaterial(String materialId);
    boolean updateMaterial(String materialObjectMapper);
    boolean deleteMaterial(String materialId);
    Material getMaterial(String materialId);
}
