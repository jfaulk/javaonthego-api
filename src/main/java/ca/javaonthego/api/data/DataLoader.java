package ca.javaonthego.api.data;

import ca.javaonthego.api.repository.QuestionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by james on 2016-01-28.
 */
@Component
@Slf4j
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private DataBuilder dataBuilder;

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        dataBuilder.createQuestions().forEach(question -> questionRepository.save(question));
    }

}
