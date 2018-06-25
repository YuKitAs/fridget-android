package edu.kit.pse.fridget.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.kit.pse.fridget.server.models.representations.UserWithJwtRepresentation;
import edu.kit.pse.fridget.server.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UserWithJwtRepresentation> registerOrLogin(@RequestHeader("Id-Token") String googleIdToken) {
        return new ResponseEntity<>(service.registerOrLogin(googleIdToken), HttpStatus.CREATED);
    }
}