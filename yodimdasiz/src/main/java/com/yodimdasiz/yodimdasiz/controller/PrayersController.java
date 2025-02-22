package com.yodimdasiz.yodimdasiz.controller;

import com.yodimdasiz.yodimdasiz.model.Prayers;
import com.yodimdasiz.yodimdasiz.service.PrayersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/prayers")
@RequiredArgsConstructor
public class PrayersController {

    private final PrayersService service;

    @PostMapping("/create")
    public ResponseEntity<Prayers> createPrayer(@RequestBody Prayers prayer) {
        Prayers newPrayer = service.createPrayer(prayer);
        return ResponseEntity.ok(newPrayer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prayers> getPrayerById(@PathVariable Integer id) {
        Prayers prayer = service.getPrayerById(id);
        return ResponseEntity.ok(prayer);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Prayers>> getAllPrayers() {
        List<Prayers> prayers = service.getAllPrayers();
        return ResponseEntity.ok(prayers);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Prayers> updatePrayer(@PathVariable Integer id, @RequestBody Prayers updatedPrayer) {
        Prayers updatedPray = service.updatePrayer(id, updatedPrayer);
        return ResponseEntity.ok(updatedPray);
    }

    @DeleteMapping
    public ResponseEntity<String> deletePrayer(@PathVariable Integer id) {
        service.deletePrayer(id);
        return ResponseEntity.ok("Prayer successfully deleted");
    }
}