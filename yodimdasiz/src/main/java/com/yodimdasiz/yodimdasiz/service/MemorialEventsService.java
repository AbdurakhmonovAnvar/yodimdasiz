package com.yodimdasiz.yodimdasiz.service;

import com.yodimdasiz.yodimdasiz.model.MemorialEvents;
import com.yodimdasiz.yodimdasiz.repository.MemorialEventsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemorialEventsService {
    private final MemorialEventsRepository memorialEventsRepository;

    public MemorialEvents createEvent(MemorialEvents event) {
        return memorialEventsRepository.save(event);
    }

    public List<MemorialEvents> getAllEvents() {
        return memorialEventsRepository.findAll();
    }

    public MemorialEvents getEventById(Integer id) {
        return memorialEventsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
    }

    @Transactional
    public MemorialEvents updateEvent(Integer id, MemorialEvents updatedEvent) {
        MemorialEvents existingEvent = getEventById(id);
        existingEvent.setMemorial(updatedEvent.getMemorial());
        existingEvent.setOrganizer(updatedEvent.getOrganizer());
        existingEvent.setEventName(updatedEvent.getEventName());
        existingEvent.setDescription(updatedEvent.getDescription());
        existingEvent.setEventDate(updatedEvent.getEventDate());
        existingEvent.setLocation(updatedEvent.getLocation());
        return memorialEventsRepository.save(existingEvent);
    }

    public void deleteEvent(Integer id) {
        if (!memorialEventsRepository.existsById(id)) {
            throw new RuntimeException("Event not found");
        }
        memorialEventsRepository.deleteById(id);
    }

}
