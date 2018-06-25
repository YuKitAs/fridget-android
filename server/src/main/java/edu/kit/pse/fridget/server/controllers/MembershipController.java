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

import edu.kit.pse.fridget.server.models.Membership;
import edu.kit.pse.fridget.server.models.commands.SaveMembershipCommand;
import edu.kit.pse.fridget.server.models.representations.UserMembershipRepresentation;
import edu.kit.pse.fridget.server.services.MembershipService;

@RestController
@RequestMapping("/memberships")
public class MembershipController {
    private final MembershipService service;

    @Autowired
    public MembershipController(MembershipService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserMembershipRepresentation>> getAllUsers(@RequestParam("flatshare") String flatshareId) {
        return new ResponseEntity<>(service.getAllUsers(flatshareId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<UserMembershipRepresentation> getUser(@RequestParam("flatshare") String flatshareId,
            @RequestParam("user") String userId) {
        return new ResponseEntity<>(service.getUser(flatshareId, userId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Membership> saveMembership(@RequestBody SaveMembershipCommand saveMembershipCommand) {
        return new ResponseEntity<>(service.saveMembership(saveMembershipCommand.getAccessCode(), saveMembershipCommand.getUserId(),
                saveMembershipCommand.getBuilder()), HttpStatus.CREATED);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<Void> deleteMembership(@RequestParam("flatshare") String flatshareId, @RequestParam("user") String userId) {
        service.deleteMembership(flatshareId, userId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
