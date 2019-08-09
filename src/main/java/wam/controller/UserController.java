package wam.controller;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wam.config.Logging;
import wam.model.ResponseDescription;
import wam.model.User;
import wam.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * REST controller for Users
 */
@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final Logging logger = new Logging(LogManager.getLogger(this.getClass()));

    @Autowired
    UserRepository userRepository;

    /**
     * Get all users
     *
     * @link http://localhost:8080/user/
     */
    @GetMapping(value = "/")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<User> getAllUsers(HttpServletRequest request) {

        return userRepository.findAll();
    }

    /**
     * Get total number of users
     *
     * @link localhost:8080/user/total
     */
    @GetMapping(value = "/total")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Long total() {
        return userRepository.count();
    }

    /**
     * Get a user by id
     *
     * @link localhost:8080/user/id/1
     */
    @GetMapping("/id/{id}")
    @ResponseBody
    public User getUserById(@PathVariable("id") final Long id, HttpServletRequest request) {

        return userRepository.findByUserId(id);
    }

    /**
     * Create a user
     *
     * @link http://localhost:8080/user/username/email/city/postalCode/country
     */
    @PostMapping("/username/{username}/email/{email}/city/{city}/postalCode/{postalCode}/country/{country}")
    @ResponseBody
    public ResponseEntity createUser(
            @PathVariable("username") final String username
            , @PathVariable("email") final String email
            , @PathVariable("city") final String city
            , @PathVariable("postalCode") final String postalCode
            , @PathVariable("country") final String country
            , HttpServletRequest request) {

        logger.requestStart(request);

        try {

            User user = new User(username, email, city, postalCode, country);
            userRepository.save(user);
            logger.requestStop();
            return new ResponseEntity<>(user, HttpStatus.CREATED);

        } catch (Exception e) {

            logger.requestStop();
            return new ResponseEntity<>(new ResponseDescription("Didn't create")
                    , HttpStatus.NOT_ACCEPTABLE);
        }
    }

    /**
     * Update a user data
     *
     * @link localhost:8080/user/username/email/city/postalCode/country
     */
    @PutMapping(value = "/id/{id}/username/{username}/email/{email}/city/{city}/postalCode/{postalCode}/country/{country}"
            , produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity updateUser(
            @PathVariable("id") final Long id
            , @PathVariable("username") final String username
            , @PathVariable("email") final String email
            , @PathVariable("city") final String city
            , @PathVariable("postalCode") final String postalCode
            , @PathVariable("country") final String country
            , HttpServletRequest request) {

        logger.requestStart(request);

        try {

            User user = userRepository.findByUserId(id);
            user.setUsername(username);
            user.setEmail(email);
            user.setCity(city);
            user.setPostalCode(postalCode);
            user.setCountry(country);
            userRepository.save(user);

            logger.requestStop();
            return new ResponseEntity<>(user, HttpStatus.CREATED);

        } catch (Exception e) {

            logger.requestStop();
            return new ResponseEntity<>(new ResponseDescription("Didn't update")
                    , HttpStatus.NOT_ACCEPTABLE);
        }
    }

    /**
     * Delete a user
     *
     * @link localhost:8080/user/id/1
     */
    @DeleteMapping(value = "/id/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteUser(@PathVariable("id") final Long id, HttpServletRequest request) {

        logger.requestStart(request);

        User user = userRepository.findByUserId(id);

        if (user == null) {

            logger.requestStop();
            return new ResponseEntity<>(new ResponseDescription("User with id "
                    + id + " was not found."), HttpStatus.NOT_FOUND);
        }

        userRepository.deleteById(id);
        logger.requestStop();
        return new ResponseEntity<>(new ResponseDescription("User with id "
                + id + " was deleted. " + total() + " users total"), HttpStatus.OK);
    }

    /**
     * Delete all users
     *
     * @link localhost:8080/user/
     */
    @DeleteMapping(value = "/")
    @ResponseBody
    public ResponseEntity<?> deleteAllUsers(HttpServletRequest request) {

        logger.requestStart(request);

        userRepository.deleteAll();
        logger.requestStop();
        return new ResponseEntity<>(new ResponseDescription("All users was deleted.")
                , HttpStatus.OK);
    }

    /**
     * Filter a user by username
     *
     * @link localhost:8080/user/filter/user/1
     */
    @GetMapping("/filter/username/{username}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<User> getUsernameFilterUser(@PathVariable("username") final String username, HttpServletRequest request) {

        logger.requestStart(request);

        List<User> users = userRepository.findByUsernameContains(username);

        logger.requestStop(users);
        return users;
    }

}
