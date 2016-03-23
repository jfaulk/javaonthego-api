package ca.javaonthego.api.controller;

import ca.javaonthego.api.model.User;
import ca.javaonthego.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public User getUserByUsername(@PathVariable("userName") String userName) {
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
     * @return created user
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
     * @param userName the user to update
     */
    @CrossOrigin
    @RequestMapping(value = {"/api/user/{userName}"}, method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public User update(@RequestBody User user, @PathVariable("userName") String userName,
                           HttpServletResponse httpResponse) {
        if (!userRepository.exists(userRepository.findByUserName(userName).getId())) {
            httpResponse.setStatus(HttpStatus.NOT_FOUND.value());
        } else {
            User toUpdate = userRepository.findByUserName(userName);

            if (user.getUserName() != null) {
                toUpdate.setUserName(user.getUserName());
            }

            if (user.getEmail() != null) {
                toUpdate.setEmail(user.getEmail());
            }

            if (user.getPassword() != null) {
                toUpdate.setPassword(user.getPassword());
            }

            if (user.getLastChallenge() != null) {
                toUpdate.setLastChallenge(user.getLastChallenge());
            }

            if (user.getLevel() != null) {
                toUpdate.setPassword(user.getPassword());
            }

            if (user.getAchievements() != null) {
                toUpdate.setAchievements(user.getAchievements());
            }

            userRepository.save(toUpdate);
            httpResponse.setStatus(HttpStatus.OK.value());
            return toUpdate;
        }
        return null;
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

    @CrossOrigin
    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    @ResponseBody
    public boolean checkLogin(@RequestBody User user) {
        String username = user.getUserName();
        String password = user.getPassword();

        User toCheck = userRepository.findByUserName(username);
        return toCheck.getUserName().equals(username) & toCheck.getPassword().equals(password);
    }

    @CrossOrigin
    @RequestMapping(value = "/session", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> loginUser(Principal principal) {
        HashMap<String, String> result = new HashMap<>();
        result.put("username", principal.getName());
        return result;
    }

    @RequestMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(HttpSession session) {
        session.invalidate();
    }
}
