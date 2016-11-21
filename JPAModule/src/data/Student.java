package data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
public class Student extends User implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer yearRegistry;
    @JsonIgnore
    @ManyToMany
    private List<Course> coursesList;

    public Student(String name, String birthdate, String institutionalEmail, String email, String address,
                   String telephone, byte[] passwordHash, Integer yearRegistry) {
        this.setUserType("STUDENT");
        this.setName(name);
        this.setInstitutionalEmail(institutionalEmail);
        this.setEmail(email);
        this.setAddress(address);
        this.setTelephone(telephone);
        this.setPasswordHash(passwordHash);
        this.setBirthdate(birthdate);
        this.setYearRegistry(yearRegistry);
    }

    public Student(String name, String institutionalEmail, byte[] passwordHash){
        this.setUserType("STUDENT");
        this.setName(name);
        this.setInstitutionalEmail(institutionalEmail);
        this.setPasswordHash(passwordHash);
    }

    public Student() { }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getYearRegistry() {
        return yearRegistry;
    }

    public void setYearRegistry(Integer yearRegistry) {
        this.yearRegistry = yearRegistry;
    }

    public List<Course> getCoursesList() {
        return coursesList;
    }

    public void setCoursesList(List<Course> coursesList) {
        this.coursesList = coursesList;
    }
}
