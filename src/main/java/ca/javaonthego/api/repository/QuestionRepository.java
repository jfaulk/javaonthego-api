package ca.javaonthego.api.repository;

import ca.javaonthego.api.model.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by james on 2016-01-25.
 */
@Repository
public interface QuestionRepository extends CrudRepository<Question, Long> {
    List<Question> findAllByChapter(int chapter);
}
