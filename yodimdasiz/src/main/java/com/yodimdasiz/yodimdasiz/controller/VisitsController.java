package com.yodimdasiz.yodimdasiz.controller;

import com.yodimdasiz.yodimdasiz.model.Visits;
import com.yodimdasiz.yodimdasiz.service.VisitsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/visits")
@RequiredArgsConstructor
public class VisitsController {

    private final VisitsService service;

    @PostMapping("/create")
    public ResponseEntity<Visits> createVisit(@RequestBody Visits visit) {
        Visits newVisit = service.createVisit(visit);
        return ResponseEntity.ok(newVisit);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Visits> getVisitById(@PathVariable Integer id) {
        Visits visit = service.getVisitById(id);
        return ResponseEntity.ok(visit);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Visits>> getAllVisits() {
        List<Visits> visits = service.getAllVisits();
        return ResponseEntity.ok(visits);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Visits> updateVisit(@PathVariable Integer id, @RequestBody Visits updatedVisit) {
        Visits updatedV = service.updateVisit(id, updatedVisit);
        return ResponseEntity.ok(updatedV);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteVisit(@PathVariable Integer id) {
        service.deleteVisit(id);
        return ResponseEntity.ok("Visit successfully deleted");
    }
}
