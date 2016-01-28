package ca.javaonthego.api.data;

import ca.javaonthego.api.model.Question;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Created by james on 2016-01-28.
 */
@Component
public class DataBuilder {
    public List<Question> createQuestions() {
        Question question1 = new Question("Question 1", 1, new String[]{"Answer 1", "Answer 2", "Answer3", "Answer4"});
        Question question2 = new Question("Question 1", 1, new String[]{"Answer 1", "Answer 2", "Answer3", "Answer4"});
        Question question3 = new Question("Question 1", 1, new String[]{"Answer 1", "Answer 2", "Answer3", "Answer4"});
        Question question4 = new Question("Question 1", 1, new String[]{"Answer 1", "Answer 2", "Answer3", "Answer4"});
        Question question5 = new Question("Question 1", 1, new String[]{"Answer 1", "Answer 2", "Answer3", "Answer4"});
        Question question6 = new Question("Question 1", 1, new String[]{"Answer 1", "Answer 2", "Answer3", "Answer4"});

        return Arrays.asList(question1, question2, question3, question4, question5, question6);
    }
}
