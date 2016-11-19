package ejb;

import javax.ejb.Remote;

@Remote
public interface AuthEJBRemote {
    String loginWithCredentials(String email, String passwordHash);
    String dummyMethod();
}