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

import edu.kit.pse.fridget.server.models.CoolNote;
import edu.kit.pse.fridget.server.services.CoolNoteService;
import edu.kit.pse.fridget.server.services.TaggedMemberService;

@RestController
@RequestMapping("/cool-notes")
public class CoolNoteController {
    private final CoolNoteService coolNoteService;
    private final TaggedMemberService taggedMemberService;

    @Autowired
    public CoolNoteController(CoolNoteService coolNoteService, TaggedMemberService taggedMemberService) {
        this.coolNoteService = coolNoteService;
        this.taggedMemberService = taggedMemberService;
    }

    @GetMapping
    public ResponseEntity<List<CoolNote>> getAllCoolNotes(@RequestParam("flatshare") String flatshareId) {
        return new ResponseEntity<>(coolNoteService.getAllCoolNotes(flatshareId), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoolNote> getCoolNote(@PathVariable String id) {
        return new ResponseEntity<>(coolNoteService.getCoolNote(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CoolNote> saveCoolNote(@RequestBody CoolNote coolNote) {
        CoolNote newCoolNote = CoolNote.buildForCreation(coolNote);
        if (!coolNote.getTaggedMembershipIds().isEmpty()) {
            coolNote.getTaggedMembershipIds()
                    .forEach(membershipId -> taggedMemberService.saveTaggedMember(membershipId, newCoolNote.getId()));
        }
        coolNoteService.saveCoolNote(newCoolNote);

        return new ResponseEntity<>(CoolNote.buildForFetching(newCoolNote, coolNote.getTaggedMembershipIds()), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteCoolNote(@PathVariable String id) {
        coolNoteService.deleteCoolNote(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
