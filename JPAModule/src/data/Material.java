package data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Material {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String materialId;
    private String filename;
    private String type;
    @JsonIgnore
    @ManyToOne
    private Course course;
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private User author;

    public Material() { }

    public Material(String filename, User author, String type, Course course) {
        this.filename = filename;
        this.author = author;
        this.type = type;
        this.course = course;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
