package ejb;

import javax.ejb.Remote;

@Remote
public interface AuthEJBRemote {
    String getUserSessionToken(String email, String password);
    boolean validateSession(String sessionToken);
    boolean createProfessorAccount(String name, String institutionalEmail, String password);
    boolean createStudentAccount(String name, String institutionalEmail, String password);
    String readAccount(String sessionToken);
    boolean updateAcount(String sessionToken);
    void logout(String sessionToken);
    String getUserType(String sessionToken);
}