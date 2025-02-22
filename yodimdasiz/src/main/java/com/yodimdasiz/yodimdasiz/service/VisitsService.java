package com.yodimdasiz.yodimdasiz.service;

import com.yodimdasiz.yodimdasiz.model.Visits;
import com.yodimdasiz.yodimdasiz.repository.VisitsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VisitsService {
    private final VisitsRepository visitsRepository;

    public Visits createVisit(Visits visit) {
        return visitsRepository.save(visit);
    }

    public List<Visits> getAllVisits() {
        return visitsRepository.findAll();
    }

    public Visits getVisitById(Integer id) {
        return visitsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Visit not found"));
    }

    @Transactional
    public Visits updateVisit(Integer id, Visits updatedVisit) {
        Visits existingVisit = getVisitById(id);
        existingVisit.setMemorial(updatedVisit.getMemorial());
        existingVisit.setUser(updatedVisit.getUser());
        existingVisit.setIpAddress(updatedVisit.getIpAddress());
        return visitsRepository.save(existingVisit);
    }

    public void deleteVisit(Integer id) {
        Visits visit = getVisitById(id);
        visitsRepository.delete(visit);
    }
}
