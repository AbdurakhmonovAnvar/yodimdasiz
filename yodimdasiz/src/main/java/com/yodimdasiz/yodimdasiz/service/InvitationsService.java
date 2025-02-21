package com.yodimdasiz.yodimdasiz.service;

import com.yodimdasiz.yodimdasiz.model.Invitations;
import com.yodimdasiz.yodimdasiz.repository.InvitationsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvitationsService {
    private final InvitationsRepository invitationsRepository;

    public Invitations createInvitation(Invitations invitation) {
        return invitationsRepository.save(invitation);
    }

    public List<Invitations> getAllInvitations() {
        return invitationsRepository.findAll();
    }

    public Invitations getInvitationById(Integer id) {
        return invitationsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invitation not found"));
    }

    @Transactional
    public Invitations updateInvitation(Integer id, Invitations updatedInvitation) {
        Invitations existingInvitation = getInvitationById(id);
        existingInvitation.setMemorialEvent(updatedInvitation.getMemorialEvent());
        existingInvitation.setUser(updatedInvitation.getUser());
        existingInvitation.setStatus(updatedInvitation.getStatus());
        return invitationsRepository.save(existingInvitation);
    }

    public void deleteInvitation(Integer id) {
        Invitations invitation = getInvitationById(id);
        invitationsRepository.delete(invitation);
    }
}
