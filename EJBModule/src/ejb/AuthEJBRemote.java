package ejb;

import data.Token;

import javax.ejb.Remote;

@Remote
public interface AuthEJBRemote {
    String createUserSessionToken(String email, String password);
    boolean validateSession(String sessionToken);
    boolean createAdministratorAccount(String name, String institutionalEmail, String password);
    boolean createProfessorAccount(String name, String institutionalEmail, String password);
    boolean createStudentAccount(String name, String institutionalEmail, String password);
    String readAccount(String sessionToken);
    Token getSessionToken(String sessionToken);
    boolean updateAcount(String sessionToken);
    void logout(String sessionToken);
}