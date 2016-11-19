package data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

/**
 * Created by tomasfrancisco on 13/11/2016.
 */
@Entity
public class Professor extends User implements Serializable {

    private String Category;
    private String Office;
    private String internalTelephone;
    private Double salary;

    @OneToMany
    private List<Course> courseList;

    public Professor(String name, String email, String passwordHash) {
        this.setName(name);
        this.setEmail(email);
        this.setPasswordHash(passwordHash);
    }

    public Professor() {

    }
}
