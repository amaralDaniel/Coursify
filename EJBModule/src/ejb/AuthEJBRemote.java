package ejb;

import javax.ejb.Remote;

@Remote
public interface AuthEJBRemote {
    String loginWithCredentials(String email, String passwordHash);
    String dummyMethod();
    boolean createProfessorAccount(String name, String email, String password);
    boolean createStudentAccount(String name, String email, String password);
    boolean readAccount(String sessionToken);
    boolean validateSession(String sessionToken);
    void logout(String sessionToken);
}