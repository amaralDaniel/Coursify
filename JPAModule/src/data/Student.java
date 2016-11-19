package data;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.List;

/**
 * Created by tomasfrancisco on 13/11/2016.
 */
@Entity
public class Student extends User implements Serializable {

    private Integer yearRegistry;

    @ManyToMany(mappedBy="studentsList")
    private List<Course> coursesList;
}
