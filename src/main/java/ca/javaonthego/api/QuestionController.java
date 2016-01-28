package ca.javaonthego.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by james on 2016-01-28.
 */
@RestController
public class QuestionController {
    @Autowired
    private QuestionRepository questionRepository;

    @RequestMapping(value = "/api/questions/{id}", method = RequestMethod.GET)
    public Question getQuestion(@PathVariable("id") Long id) {
        return questionRepository.findOne(id);
    }

    @RequestMapping(value = "/api/questions}", method = RequestMethod.GET)
    public List<Question> getAllChapterQuestions() {
        return (List<Question>) questionRepository.findAll();
    }
}
