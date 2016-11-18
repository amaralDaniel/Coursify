package data;

import javax.persistence.*;

/**
 * Created by tomasfrancisco on 09/11/2016.
 */
@Entity
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMaterial;

    private String title;
    private String author;
    private String type; //pdf, zip, etc

    @ManyToOne
    private Course course;

    public Material(){}

    public Material(String title, String author, String type){
        super();
        this.title=title;
        this.author = author;
        this.type = type;
    }




}
