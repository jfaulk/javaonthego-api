package ca.javaonthego.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by james on 2016-02-10.
 */
@Entity
public class User {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Getter
    @Setter
    private String userName;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private String dateCreated = "01/01/1970";

    @Getter
    @Setter
    private int lastChallenge = 0;

    @Getter
    @Setter
    private int level = 0;

    @Getter
    @Setter
    private boolean[] achievements = {false, false, false, false, false};

    public User() { }

    public User(String userName, String email, String password) {
        super();
        this.userName = userName;
        this.email = email;
        this.password = password;
    }
}
