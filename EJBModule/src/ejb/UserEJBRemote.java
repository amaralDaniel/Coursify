package ejb;

import data.*;

import javax.ejb.Remote;
import javax.persistence.Query;
import java.util.List;

@Remote
public interface UserEJBRemote {
    String getUserType(String sessionToken);
    String getAllUsers(String sessionToken);
    String getStudents(String sessionToken);
    String getProfessors(String sessionToken);
}
