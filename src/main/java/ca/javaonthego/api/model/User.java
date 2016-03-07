package ca.javaonthego.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    private String dateCreated = creationDate();
    @Getter
    @Setter
    private int lastChallenge = 0;
    @Getter
    @Setter
    private int level = 0;
    @Getter
    @Setter
    private boolean[] achievements = {true, true, false, false, false};

    public User() { }

    public User(String userName, String email, String password) {
        super();
        //DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        //Date date = new Date();
        this.userName = userName;
        this.email = email;
        this.password = password;
        //this.dateCreated = dateFormat.format(date);
        this.dateCreated = creationDate();
    }

    private String creationDate() {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }
}