package data;

import javax.persistence.Entity;
import java.io.Serializable;

/**
 * Created by tomasfrancisco on 13/11/2016.
 */
@Entity
public class Professor extends User implements Serializable {

    private String Category;
    private String Office;
    private String internalTelephone;
    private Double salary;
}
