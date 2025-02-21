package com.yodimdasiz.yodimdasiz.controller;

import com.yodimdasiz.yodimdasiz.model.Invitations;
import com.yodimdasiz.yodimdasiz.service.InvitationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invitations")
@RequiredArgsConstructor
public class InvitationsController {
    private final InvitationsService invitationsService;

    @PostMapping
    public ResponseEntity<Invitations> createInvitation(@RequestBody Invitations invitation) {
        return ResponseEntity.ok(invitationsService.createInvitation(invitation));
    }

    @GetMapping
    public ResponseEntity<List<Invitations>> getAllInvitations() {
        return ResponseEntity.ok(invitationsService.getAllInvitations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Invitations> getInvitationById(@PathVariable Integer id) {
        return ResponseEntity.ok(invitationsService.getInvitationById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Invitations> updateInvitation(@PathVariable Integer id, @RequestBody Invitations updatedInvitation) {
        return ResponseEntity.ok(invitationsService.updateInvitation(id, updatedInvitation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvitation(@PathVariable Integer id) {
        invitationsService.deleteInvitation(id);
        return ResponseEntity.noContent().build();
    }
}
