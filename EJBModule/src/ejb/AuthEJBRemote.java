package ejb;

import data.Token;

import javax.ejb.Remote;

@Remote
public interface AuthEJBRemote {
    boolean loginWithCredentials(String email, String passwordHash);
    boolean verifyToken(Token providedToken);
    boolean loginWithToken(String providedToken);
    String dummyMethod();
    boolean createProfessorAccount(String name, String email, String password);
    boolean createStudentAccount(String name, String email, String password);
    boolean readAccount(Token providedToken);

}