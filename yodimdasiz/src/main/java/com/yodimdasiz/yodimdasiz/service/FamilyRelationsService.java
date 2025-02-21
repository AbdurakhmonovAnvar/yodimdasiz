package com.yodimdasiz.yodimdasiz.service;

import com.yodimdasiz.yodimdasiz.model.FamilyRelations;
import com.yodimdasiz.yodimdasiz.repository.FamilyRelationsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FamilyRelationsService {
    private final FamilyRelationsRepository familyRelationsRepository;

    public FamilyRelations createRelation(FamilyRelations relation) {
        return familyRelationsRepository.save(relation);
    }

    public List<FamilyRelations> getAllRelations() {
        return familyRelationsRepository.findAll();
    }

    public FamilyRelations getRelationById(Integer id) {
        return familyRelationsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Family relation not found"));
    }

    @Transactional
    public FamilyRelations updateRelation(Integer id, FamilyRelations updatedRelation) {
        FamilyRelations existingRelation = getRelationById(id);
        existingRelation.setUser(updatedRelation.getUser());
        existingRelation.setMemorial(updatedRelation.getMemorial());
        existingRelation.setRelationType(updatedRelation.getRelationType());
        return familyRelationsRepository.save(existingRelation);
    }

    public void deleteRelation(Integer id) {
        FamilyRelations relation = getRelationById(id);
        familyRelationsRepository.delete(relation);
    }
}
