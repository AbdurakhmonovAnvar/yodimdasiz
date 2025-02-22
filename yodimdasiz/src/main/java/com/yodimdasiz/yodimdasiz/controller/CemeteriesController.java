package com.yodimdasiz.yodimdasiz.controller;

import com.yodimdasiz.yodimdasiz.model.Cemeteries;
import com.yodimdasiz.yodimdasiz.service.CemeteriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cemeteries")
@RequiredArgsConstructor
public class CemeteriesController {
    private final CemeteriesService cemeteriesService;

    @PostMapping
    public ResponseEntity<Cemeteries> createCemetery(@RequestBody Cemeteries cemetery) {
        return ResponseEntity.ok(cemeteriesService.createCemetery(cemetery));
    }

    @GetMapping
    public ResponseEntity<List<Cemeteries>> getAllCemeteries() {
        return ResponseEntity.ok(cemeteriesService.getAllCemeteries());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cemeteries> getCemeteryById(@PathVariable Integer id) {
        return ResponseEntity.ok(cemeteriesService.getCemeteryById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cemeteries> updateCemetery(@PathVariable Integer id, @RequestBody Cemeteries updatedCemetery) {
        return ResponseEntity.ok(cemeteriesService.updateCemetery(id, updatedCemetery));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCemetery(@PathVariable Integer id) {
        cemeteriesService.deleteCemetery(id);
        return ResponseEntity.noContent().build();
    }
}
