package data;

import javax.persistence.Entity;
import java.io.Serializable;
import java.sql.Date;

@Entity
public class Administrator extends User implements Serializable {
    public Administrator(String name, Date birthdate, String institutionalEmail, String email, String address, String telephone, String passwordHash) {
        this.setName(name);
        this.setInstitutionalEmail(institutionalEmail);
        this.setEmail(email);
        this.setAddress(address);
        this.setTelephone(telephone);
        this.setPasswordHash(passwordHash);
        this.setBirthdate(birthdate);
    }

    public Administrator() {

    }
}
