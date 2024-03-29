package data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Course implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String courseId;
    private String name;
    private String description;
    @JsonIgnore
    @ManyToOne(fetch= FetchType.LAZY)
    private Professor professor;
    @JsonIgnore
    @ManyToMany(mappedBy="coursesList")
    private List<Student> studentsList;
    @JsonIgnore
    @OneToMany(mappedBy="course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Material> materialList;

    public Course() { }

    public Course(String name, String description, Professor professor){
        super();
        this.name = name;
        this.description = description;
        this.professor = professor;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Student> getStudentsList() {
        return studentsList;
    }

    public void setStudentsList(List<Student> studentsList) {
        this.studentsList = studentsList;
    }

    public List<Material> getMaterialList() {
        return materialList;
    }

    public void setMaterialList(List<Material> materialList) {
        this.materialList = materialList;
    }
}
