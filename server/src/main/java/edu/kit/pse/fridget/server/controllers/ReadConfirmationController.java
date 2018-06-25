package edu.kit.pse.fridget.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.transaction.Transactional;

import edu.kit.pse.fridget.server.models.ReadConfirmation;
import edu.kit.pse.fridget.server.models.User;
import edu.kit.pse.fridget.server.services.ReadConfirmationService;

@RestController
@RequestMapping("/read-confirmations")
public class ReadConfirmationController {
    private final ReadConfirmationService service;

    @Autowired
    public ReadConfirmationController(ReadConfirmationService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(@RequestParam("cool-note") String id) {
        return new ResponseEntity<>(service.getAllUsers(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ReadConfirmation> saveReadConfirmation(@RequestBody ReadConfirmation readConfirmation) {
        return new ResponseEntity<>(service.saveReadConfirmation(readConfirmation), HttpStatus.CREATED);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<Void> deleteReadConfirmation(@RequestParam("cool-note") String coolNoteId, @RequestParam("user") String userId) {
        service.deleteReadConfirmation(coolNoteId, userId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
