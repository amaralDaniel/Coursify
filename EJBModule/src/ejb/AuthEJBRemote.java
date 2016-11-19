package ejb;

import javax.ejb.Remote;

@Remote
public interface AuthEJBRemote {
    String loginWithCredentials(String email, String passwordHash);
    boolean verifyToken(String providedToken);
    boolean loginWithToken(String providedToken);
    String dummyMethod();
    boolean signUp(String name, String email, String password);
}