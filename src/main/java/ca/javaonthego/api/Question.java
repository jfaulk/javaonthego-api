package ca.javaonthego.api;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by james on 2016-01-25.
 */
@Entity
public class Question {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Getter
    @Setter
    private int chapter;
    @Getter
    @Setter
    private String question;
    @Getter
    @Setter
    private String[] answers;

    public Question() {
    }

    public Question(String question, int chapter, String[] answers) {
        this.question = question;
        this.chapter = chapter;
        this.answers = answers;
    }
}
