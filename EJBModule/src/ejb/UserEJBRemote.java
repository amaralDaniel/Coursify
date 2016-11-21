package ejb;

import javax.ejb.Remote;

@Remote
public interface UserEJBRemote {
    String getUserType(String sessionToken);
    String getAllUsers(String sessionToken);
    String getStudents(String sessionToken);
    String getProfessors(String sessionToken);
}
