package edu.kit.pse.fridget.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.transaction.Transactional;

import edu.kit.pse.fridget.server.models.ImageNote;
import edu.kit.pse.fridget.server.services.ImageNoteService;

@RestController
@RequestMapping("/image-notes")
public class ImageNoteController {
    private final ImageNoteService service;

    @Autowired
    public ImageNoteController(ImageNoteService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ImageNote>> getAllImageNotes(@RequestParam("flatshare") String flatshareId) {
        return new ResponseEntity<>(service.getAllImageNotes(flatshareId), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImageNote> getImageNote(@PathVariable String id) {
        return new ResponseEntity<>(service.getImageNote(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ImageNote> saveImageNote(@RequestBody ImageNote imageNote) {
        return new ResponseEntity<>(service.saveImageNote(imageNote), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteImageNote(@PathVariable String id) {
        service.deleteImageNote(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
