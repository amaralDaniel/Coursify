package data;

import javax.persistence.*;
import java.util.List;

/**
 * Created by tomasfrancisco on 09/11/2016.
 */
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCourse;

    private String name;

    @ManyToOne(fetch= FetchType.LAZY)
    private Professor professor;

    @ManyToMany(mappedBy="coursesList")
    private List<Student> students;

    @OneToMany(mappedBy="course")
    private List<Material> materialList;

    public Course() {
    }

    public Course(String name, Professor teacher){
        super();
        this.name = name;
        this.professor = teacher;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
