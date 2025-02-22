package com.yodimdasiz.yodimdasiz.service;

import com.yodimdasiz.yodimdasiz.model.Cemeteries;
import com.yodimdasiz.yodimdasiz.repository.CemeteriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CemeteriesService {
    private final CemeteriesRepository cemeteriesRepository;

    public Cemeteries createCemetery(Cemeteries cemetery) {
        return cemeteriesRepository.save(cemetery);
    }

    public List<Cemeteries> getAllCemeteries() {
        return cemeteriesRepository.findAll();
    }

    public Cemeteries getCemeteryById(Integer id) {
        return cemeteriesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cemetery not found"));
    }

    @Transactional
    public Cemeteries updateCemetery(Integer id, Cemeteries updatedCemetery) {
        Cemeteries existingCemetery = getCemeteryById(id);
        existingCemetery.setName(updatedCemetery.getName());
        existingCemetery.setAddress(updatedCemetery.getAddress());
        existingCemetery.setLatitude(updatedCemetery.getLatitude());
        existingCemetery.setLongitude(updatedCemetery.getLongitude());
        existingCemetery.setDescription(updatedCemetery.getDescription());
        return cemeteriesRepository.save(existingCemetery);
    }

    public void deleteCemetery(Integer id) {
        Cemeteries cemetery = getCemeteryById(id);
        cemeteriesRepository.delete(cemetery);
    }
}
