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
 * User Controller exposes a series of RESTful endpoints
 */
@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    /**
     * Get user using id. Returns HTTP 404 if user not found
     *
     * @param userId
     * @return retrieved user
     */
    @RequestMapping(value = "/api/users/{userId}", method = RequestMethod.GET)
    public User getUser(@PathVariable("userId") Long userId) {
        return userRepository.findOne(userId);
    }

    /**
     * Get user using username. Returns HTTP 404 if user not found
     *
     * @param userName
     * @return retrieved user
     */
    @RequestMapping(value = "/api/user/{userName}", method = RequestMethod.GET)
    public List<User> getUserByUsername(@PathVariable("userName") String userName) {
        return userRepository.findByUserName(userName);
    }

    /**
     * Gets all users.
     *
     * @return the users
     */
    @CrossOrigin
    @RequestMapping(value = "/api/users", method = RequestMethod.GET)
    public List<User> findAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    /**
     * Create a new user and return in response with HTTP 201
     *
     * @return created customer
     */
    @CrossOrigin
    @RequestMapping(value = "/api/users", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    @ResponseBody
    public User createUser(@RequestBody User user, HttpServletResponse httpResponse, WebRequest request) {
        User createdUser;
        createdUser = userRepository.save(user);
        httpResponse.setStatus(HttpStatus.CREATED.value());
        httpResponse.setHeader("Location", String.format("%s/api/users/%s", request.getContextPath(), user.getId()));

        return createdUser;
    }

    /**
     * Update user with given user id.
     *
     * @param userId the customer
     */
    @CrossOrigin
    @RequestMapping(value = {"/api/users/{userId}"}, method = {RequestMethod.PUT})
    public void updateUser(@RequestBody User user, @PathVariable("userId") Long userId,
                           HttpServletResponse httpResponse) {

        if (!userRepository.exists(userId)) {
            httpResponse.setStatus(HttpStatus.NOT_FOUND.value());
        } else {
            userRepository.save(user);
            httpResponse.setStatus(HttpStatus.NO_CONTENT.value());
        }
    }

    @RequestMapping(value = {"/api/user/{userName}"}, method = {RequestMethod.PUT})
    public void updateUserByUserName(@RequestBody User user, @PathVariable("userName") String userName,
                                     HttpServletResponse httpResponse) {
        userRepository.save(user);
        httpResponse.setStatus(HttpStatus.NO_CONTENT.value());
    }

    /**
     * Deletes the user with given user id if it exists and returns HTTP 204.
     *
     * @param userId the user id
     */
    @CrossOrigin
    @RequestMapping(value = "/api/users/{userId}", method = RequestMethod.DELETE)
    public void removeUser(@PathVariable("userId") Long userId, HttpServletResponse httpResponse) {

        if (userRepository.exists(userId)) {
            userRepository.delete(userId);
        }

        httpResponse.setStatus(HttpStatus.NO_CONTENT.value());
    }
}
