package edu.kit.pse.fridget.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import edu.kit.pse.fridget.server.models.FrozenNote;
import edu.kit.pse.fridget.server.services.FrozenNoteService;

@RestController
@RequestMapping("/frozen-notes")
public class FrozenNoteController {
    private final FrozenNoteService service;

    @Autowired
    public FrozenNoteController(FrozenNoteService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<FrozenNote>> getAllFrozenNotes(@RequestParam("flatshare") String flatshareId) {
        return new ResponseEntity<>(service.getAllFrozenNotes(flatshareId), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FrozenNote> getFrozenNote(@PathVariable String id) {
        return new ResponseEntity<>(service.getFrozenNote(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FrozenNote> updateFrozenNote(@PathVariable String id, @RequestBody FrozenNote frozenNote) {
        return new ResponseEntity<>(service.updateFrozenNote(id, frozenNote), HttpStatus.OK);
    }
}
