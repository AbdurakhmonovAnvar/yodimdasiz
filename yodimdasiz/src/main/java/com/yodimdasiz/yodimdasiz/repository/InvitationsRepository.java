package com.yodimdasiz.yodimdasiz.repository;

import com.yodimdasiz.yodimdasiz.model.Invitations;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvitationsRepository extends JpaRepository<Invitations, Integer> {
    List<Invitations> findByUserId(Integer userId);
    List<Invitations> findByMemorialEventId(Integer memorialEventId);
}
