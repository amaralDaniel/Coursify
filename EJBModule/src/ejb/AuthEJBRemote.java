package ejb;

import data.Token;

import javax.ejb.Remote;

@Remote
public interface AuthEJBRemote {
    String createUserSessionToken(String email, String password);
    boolean validateSession(String sessionToken);
    boolean createAdministratorAccount(String name, String institutionalEmail, String password);
    boolean createProfessorAccount(String name, String birthdate, String institutionalEmail,
                                   String alternativeEmail, String address, String phone, String category,
                                   String office, String internalPhone, Double salary, String password);
    boolean createProfessorAccount(String name, String institutionalEmail, String password);
    boolean createStudentAccount(String name, String institutionalEmail, String password);
    String readAccount(String sessionToken);
    Token getSessionToken(String sessionToken);
    void logout(String sessionToken);
    boolean removeAccount(String institutionalEmail);
}