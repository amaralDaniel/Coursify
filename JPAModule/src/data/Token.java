package data;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Token implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String sessionToken;

    public Token() { }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getSessionToken() {
        return sessionToken;
    }
}