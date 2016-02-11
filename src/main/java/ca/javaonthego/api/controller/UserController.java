package ca.javaonthego.api.controller;

import ca.javaonthego.api.model.User;
import ca.javaonthego.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by james on 2016-02-10.
 */
@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/api/users/{userId}", method = RequestMethod.GET)
    public User getUser(@PathVariable("userId") Long userId) {
        return userRepository.findOne(userId);
    }

    @RequestMapping(value = "/api/users", method = RequestMethod.GET)
    public List<User> findAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @RequestMapping(value = "/api/user/{userName}", method = RequestMethod.GET)
    public List<User> getUserByUsername(@PathVariable("userName") String userName) {
        return userRepository.findByUserName(userName);
    }

    @RequestMapping(value = "/api/users", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public User createUser(@RequestBody User user, HttpServletResponse httpResponse, WebRequest request) {
        User createdUser;
        createdUser = userRepository.save(user);
        httpResponse.setStatus(HttpStatus.CREATED.value());
        httpResponse.setHeader("Location", String.format("%s/api/users/%s", request.getContextPath(), user.getId()));

        return createdUser;
    }
}
