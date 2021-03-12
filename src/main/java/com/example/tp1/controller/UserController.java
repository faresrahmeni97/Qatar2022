package com.example.tp1.controller;

import com.example.tp1.entities.User;
import com.example.tp1.entities.UserDto;
import com.example.tp1.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.example.tp1.service.UserService;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins ="http://localhost:4200")


public class UserController {
    private static final Logger logger = LogManager.getLogger(UserController.class);
    @Autowired
    UserRepository userv;

    @Autowired
    private UserService userService;

    @RequestMapping(value="/signup", method = RequestMethod.POST)
    public User saveUser(@RequestBody UserDto user){
        return userService.save(user);
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users")
    public List<User> getAllUsers() {
        List<User> pro = userv.findAll();

        for (User user : pro) {
            logger.debug("log:     " + user);
            System.out.println("sysout:   " + user);

        }
        return pro;

    }

   // @PreAuthorize("hasRole('USER')")
    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable(value = "id") Long Id) {
        return userv.findById(Id).orElseThrow(null);
        // .orElseThrow(() -> new ResourceNotFoundException("User", "id", Id));
    }
    //@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/userdelete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long userId) {
        User user = userv.findById(userId).orElseThrow(null);
        //.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        // userRepository.deleteById(userId);
        userv.delete(user);

        return ResponseEntity.ok().build();
    }
    @PostMapping("/adduser")

    public User createUser(@Valid @RequestBody User user) {
        return userv.save(user);
    }

    @PutMapping("/userupdate/{id}")
    public User updateUser(@PathVariable(value = "id") Long Id,
                           @Valid @RequestBody User userDetails) {

        User user = userv.findById(Id).orElseThrow(null);


        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        user.setFname(userDetails.getFname());
        user.setLname(userDetails.getLname());
        user.setUsername(userDetails.getUsername());
        User updatedUser = userv.save(user);
        return updatedUser;
    }
}


