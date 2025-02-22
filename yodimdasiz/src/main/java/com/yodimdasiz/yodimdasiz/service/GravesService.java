package com.yodimdasiz.yodimdasiz.service;

import com.yodimdasiz.yodimdasiz.model.Graves;
import com.yodimdasiz.yodimdasiz.repository.GravesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GravesService {
    private final GravesRepository gravesRepository;

    public Graves createGrave(Graves grave) {
        return gravesRepository.save(grave);
    }

    public List<Graves> getAllGraves() {
        return gravesRepository.findAll();
    }

    public Graves getGraveById(Integer id) {
        return gravesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grave not found"));
    }

    @Transactional
    public Graves updateGrave(Integer id, Graves updatedGrave) {
        Graves existingGrave = getGraveById(id);
        existingGrave.setLatitude(updatedGrave.getLatitude());
        existingGrave.setLongitude(updatedGrave.getLongitude());
        return gravesRepository.save(existingGrave);
    }

    public void deleteGrave(Integer id) {
        Graves grave = getGraveById(id);
        gravesRepository.delete(grave);
    }
}
