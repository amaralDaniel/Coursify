package data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
public class Professor extends User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String category;
    private String office;
    private String internalTelephone;
    private Double salary;

    @OneToMany(mappedBy = "professor")
    private List<Course> courseList;

    public Professor(String name, Date birthdate, String institutionalEmail, String email, String address,
                     String telephone, String passwordHash, String category, String office, String internalTelephone,
                     Double salary ) {
        this.setName(name);
        this.setInstitutionalEmail(institutionalEmail);
        this.setEmail(email);
        this.setAddress(address);
        this.setTelephone(telephone);
        this.setPasswordHash(passwordHash);
        this.setBirthdate(birthdate);
        this.setCategory(category);
        this.setOffice(office);
        this.setInternalTelephone(internalTelephone);
        this.setSalary(salary);
    }

    public Professor() {

    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getInternalTelephone() {
        return internalTelephone;
    }

    public void setInternalTelephone(String internalTelephone) {
        this.internalTelephone = internalTelephone;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }
}
