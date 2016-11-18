package ejb;

import javax.ejb.Remote;

@Remote
public interface AuthEJBRemote {
    public String dummyMethod();
}