package com.yodimdasiz.yodimdasiz.controller;

import com.yodimdasiz.yodimdasiz.model.MemorialEvents;
import com.yodimdasiz.yodimdasiz.service.MemorialEventsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/memorial-events")
@RequiredArgsConstructor
public class MemorialEventsController {
    private final MemorialEventsService memorialEventsService;

    @PostMapping
    public ResponseEntity<MemorialEvents> createEvent(@RequestBody MemorialEvents event) {
        return ResponseEntity.ok(memorialEventsService.createEvent(event));
    }

    @GetMapping
    public ResponseEntity<List<MemorialEvents>> getAllEvents() {
        return ResponseEntity.ok(memorialEventsService.getAllEvents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemorialEvents> getEventById(@PathVariable Integer id) {
        return ResponseEntity.ok(memorialEventsService.getEventById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemorialEvents> updateEvent(@PathVariable Integer id, @RequestBody MemorialEvents updatedEvent) {
        return ResponseEntity.ok(memorialEventsService.updateEvent(id, updatedEvent));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Integer id) {
        memorialEventsService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}
