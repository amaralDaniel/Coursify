package data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by tomasfrancisco on 09/11/2016.
 */
@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Double number;

    @Enumerated(EnumType.STRING)
    private Flag flag;

    private String name;
    private Date birthdate;
    private String institutionalEmail;
    private String email;
    private String address;
    private String telephone;




}
