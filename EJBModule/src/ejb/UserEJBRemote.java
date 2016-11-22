package ejb;

import dto.UserDTO;

import javax.ejb.Remote;

@Remote
public interface UserEJBRemote {
    String getUserType(String sessionToken);
    String getUserId(String sessionToken);
    String getAllUsers(String sessionToken);
    String getStudents(String sessionToken);
    String getProfessors(String sessionToken);
    UserDTO getUser(String sessionToken, String userId);
    boolean updateUser(UserDTO user);
    boolean deleteUser(String sessionToken, String userId);
}
