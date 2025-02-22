package com.yodimdasiz.yodimdasiz.controller;

import com.yodimdasiz.yodimdasiz.model.MemorialPages;
import com.yodimdasiz.yodimdasiz.service.MemorialPagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/memorep")
@RequiredArgsConstructor
public class MemorialPagesController {
    private final MemorialPagesService memorialPagesService;

    @GetMapping
    public ResponseEntity<List<MemorialPages>> getAllMemorialPages() {
        return ResponseEntity.ok(memorialPagesService.getAllMemorialPages());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemorialPages> getMemorialPageById(@PathVariable Integer id) {
        return ResponseEntity.ok(memorialPagesService.getMemorialPageById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<MemorialPages>> getMemorialPagesByUserId(@PathVariable Integer userId) {
        return ResponseEntity.ok(memorialPagesService.getMemorialPagesByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<MemorialPages> createMemorialPage(@RequestBody MemorialPages memorialPage) {
        return ResponseEntity.ok(memorialPagesService.createMemorialPage(memorialPage));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemorialPages> updateMemorialPage(@PathVariable Integer id, @RequestBody MemorialPages updatedPage) {
        return ResponseEntity.ok(memorialPagesService.updateMemorialPage(id, updatedPage));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMemorialPage(@PathVariable Integer id) {
        memorialPagesService.deleteMemorialPage(id);
        return ResponseEntity.noContent().build();
    }
}
