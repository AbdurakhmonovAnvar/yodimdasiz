package com.yodimdasiz.yodimdasiz.controller;

import com.yodimdasiz.yodimdasiz.model.FamilyRelations;
import com.yodimdasiz.yodimdasiz.service.FamilyRelationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/family-relations")
@RequiredArgsConstructor
public class FamilyRelationsController {
    private final FamilyRelationsService familyRelationsService;

    @PostMapping
    public ResponseEntity<FamilyRelations> createRelation(@RequestBody FamilyRelations relation) {
        return ResponseEntity.ok(familyRelationsService.createRelation(relation));
    }

    @GetMapping
    public ResponseEntity<List<FamilyRelations>> getAllRelations() {
        return ResponseEntity.ok(familyRelationsService.getAllRelations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FamilyRelations> getRelationById(@PathVariable Integer id) {
        return ResponseEntity.ok(familyRelationsService.getRelationById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FamilyRelations> updateRelation(@PathVariable Integer id, @RequestBody FamilyRelations updatedRelation) {
        return ResponseEntity.ok(familyRelationsService.updateRelation(id, updatedRelation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRelation(@PathVariable Integer id) {
        familyRelationsService.deleteRelation(id);
        return ResponseEntity.noContent().build();
    }
}
