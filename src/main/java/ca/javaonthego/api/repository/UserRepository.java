package ca.javaonthego.api.repository;

import ca.javaonthego.api.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by james on 2016-02-10.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUserName(String userName);
}
