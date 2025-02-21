package com.yodimdasiz.yodimdasiz.controller;

import com.yodimdasiz.yodimdasiz.model.Graves;
import com.yodimdasiz.yodimdasiz.service.GravesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/graves")
@RequiredArgsConstructor
public class GravesController {
    private final GravesService gravesService;

    @PostMapping
    public ResponseEntity<Graves> createGrave(@RequestBody Graves grave) {
        return ResponseEntity.ok(gravesService.createGrave(grave));
    }

    @GetMapping
    public ResponseEntity<List<Graves>> getAllGraves() {
        return ResponseEntity.ok(gravesService.getAllGraves());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Graves> getGraveById(@PathVariable Integer id) {
        return ResponseEntity.ok(gravesService.getGraveById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Graves> updateGrave(@PathVariable Integer id, @RequestBody Graves updatedGrave) {
        return ResponseEntity.ok(gravesService.updateGrave(id, updatedGrave));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGrave(@PathVariable Integer id) {
        gravesService.deleteGrave(id);
        return ResponseEntity.noContent().build();
    }
}
