package org.regeneration.rest.restless.controller;

import org.regeneration.rest.restless.model.User;
import org.regeneration.rest.restless.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {

    private final UserRepository userRepository;

    public UserController(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/user")
    public User getLoggedInUser(Principal loggedInUser) {
        return userRepository.findByUsername(loggedInUser.getName());
    }
}
