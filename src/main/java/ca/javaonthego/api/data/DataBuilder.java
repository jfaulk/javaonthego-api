package ca.javaonthego.api.data;

import ca.javaonthego.api.model.User;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Created by james on 2016-01-28.
 */
@Component
public class DataBuilder {
    public List<User> createUsers() {

        User user1 = new User("jeremy", "jeremy@test.com", "test");
        User user2 = new User("jon", "jon@test.com", "test");
        User user3 = new User("james", "james@test.com", "test");
        User user4 = new User("ryan", "ryan@test.com", "test");

        return Arrays.asList(user1, user2, user3, user4);
    }
}
