package edu.kit.pse.fridget.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.kit.pse.fridget.server.models.Flatshare;
import edu.kit.pse.fridget.server.models.commands.SaveFlatshareCommand;
import edu.kit.pse.fridget.server.services.FlatshareService;

@RestController
@RequestMapping("/flatshares")
public class FlatshareController {
    private final FlatshareService service;

    @Autowired
    public FlatshareController(FlatshareService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Flatshare> getFlatshare(@PathVariable String id) {
        return new ResponseEntity<>(service.getFlatshare(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Flatshare> saveFlatshare(@RequestBody SaveFlatshareCommand saveFlatshareCommand) {
        return new ResponseEntity<>(service.saveFlatshare(saveFlatshareCommand.buildFlatshare(), saveFlatshareCommand.getUserId()), HttpStatus.CREATED);
    }
}
