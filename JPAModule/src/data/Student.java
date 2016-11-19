package data;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 * Created by tomasfrancisco on 13/11/2016.
 */
@Entity
public class Student extends User implements Serializable {

    private Integer yearRegistry;

    @ManyToMany(mappedBy="studentsList")
    private List<Course> coursesList;

    public Student(String name, Date birthdate, String institutionalEmail, String email, String address, String telephone, String passwordHash, Integer yearRegistry) {
        this.setName(name);
        this.setInstitutionalEmail(institutionalEmail);
        this.setEmail(email);
        this.setAddress(address);
        this.setTelephone(telephone);
        this.setPasswordHash(passwordHash);
        this.setBirthdate(birthdate);
        this.setYearRegistry(yearRegistry);

    }

    public Student() {

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
