package ejb;

import javax.ejb.Remote;

@Remote
public interface AuthEJBRemote {
    String loginWithCredentials(String email, String passwordHash);

    boolean validateSession(String sessionToken);
    boolean signUp(String name, String email, String password);
    void logout(String sessionToken);
}