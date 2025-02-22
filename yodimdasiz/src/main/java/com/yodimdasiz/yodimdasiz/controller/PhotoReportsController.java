package com.yodimdasiz.yodimdasiz.controller;

import com.yodimdasiz.yodimdasiz.model.PhotoReports;
import com.yodimdasiz.yodimdasiz.service.PhotoReportsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/photo-reports")
@RequiredArgsConstructor
public class PhotoReportsController {

    private final PhotoReportsService service;

    @PostMapping("/create")
    public ResponseEntity<PhotoReports> createPhotoReport(@RequestBody PhotoReports photoReport) {
        PhotoReports newPhotoReport = service.createPhotoReport(photoReport);
        return ResponseEntity.ok(newPhotoReport);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhotoReports> getPhotoReportById(@PathVariable Integer id) {
        PhotoReports photoReport = service.getPhotoReportById(id);
        return ResponseEntity.ok(photoReport);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PhotoReports>> getAllPhotoReports() {
        List<PhotoReports> photoReports = service.getAllPhotoReports();
        return ResponseEntity.ok(photoReports);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PhotoReports> updatePhotoReport(@PathVariable Integer id, @RequestBody PhotoReports updatedPhotoReport) {
        PhotoReports updatedReport = service.updatePhotoReport(id, updatedPhotoReport);
        return ResponseEntity.ok(updatedReport);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePhotoReport(@PathVariable Integer id) {
        service.deletePhotoReport(id);
        return ResponseEntity.ok("Photo report successfully deleted");
    }
}
