package com.yodimdasiz.yodimdasiz.repository;

import com.yodimdasiz.yodimdasiz.model.MemorialEvents;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemorialEventsRepository extends JpaRepository<MemorialEvents, Integer> {
    List<MemorialEvents> findByMemorialId(Integer memorialId);
    List<MemorialEvents> findByOrganizerId(Integer organizerId);
}
