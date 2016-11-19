package data;

import javax.persistence.Entity;
import java.io.Serializable;

/**
 * Created by tomasfrancisco on 13/11/2016.
 */

@Entity
public class Administrator extends User implements Serializable {

    public Administrator(String name, String email, String passwordHash) {
        this.setName(name);
        this.setEmail(email);
        this.setPasswordHash(passwordHash);
    }

    public Administrator() {

    }
}
