package com.yodimdasiz.yodimdasiz.service;

import com.yodimdasiz.yodimdasiz.model.PhotoReports;
import com.yodimdasiz.yodimdasiz.repository.PhotoReportsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PhotoReportsService {
    private final PhotoReportsRepository photoReportsRepository;

    public PhotoReports createPhotoReport(PhotoReports photoReport) {
        return photoReportsRepository.save(photoReport);
    }

    public List<PhotoReports> getAllPhotoReports() {
        return photoReportsRepository.findAll();
    }

    public PhotoReports getPhotoReportById(Integer id) {
        return photoReportsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Photo report not found"));
    }

    @Transactional
    public PhotoReports updatePhotoReport(Integer id, PhotoReports updatedPhotoReport) {
        PhotoReports existingPhotoReport = getPhotoReportById(id);
        existingPhotoReport.setMaintenanceOrder(updatedPhotoReport.getMaintenanceOrder());
        existingPhotoReport.setPhotoUrl(updatedPhotoReport.getPhotoUrl());
        existingPhotoReport.setDescription(updatedPhotoReport.getDescription());
        return photoReportsRepository.save(existingPhotoReport);
    }

    public void deletePhotoReport(Integer id) {
        PhotoReports photoReport = getPhotoReportById(id);
        photoReportsRepository.delete(photoReport);
    }
}
