package edu.kit.pse.fridget.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.kit.pse.fridget.server.models.AccessCode;
import edu.kit.pse.fridget.server.services.AccessCodeService;

@RestController
@RequestMapping("/access-codes")
public class AccessCodeController {
    private final AccessCodeService service;

    @Autowired
    public AccessCodeController(AccessCodeService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AccessCode> generateAccessCode(@RequestBody AccessCode accessCode) {
        return new ResponseEntity<>(service.generateAccessCode(accessCode.getFlatshareId()), HttpStatus.CREATED);
    }
}
