package ejb;

import data.Material;
import data.MaterialType;

import javax.ejb.Remote;

@Remote
public interface MaterialEJBRemote {
    boolean createMaterial(String filename, int userId, MaterialType materialType, int courseId);
    String readMaterial(int materialId);
    boolean updateMaterial(String materialObjectMapper);
    boolean deleteMaterial(int materialId);
    Material getMaterial(int materialId);
}
