package ejb;

import javax.ejb.Stateless;

@Stateless
public class UserEJB implements UserEJBRemote {

    public String getUsers(String sessionToken) {
        return null;
    }

}
