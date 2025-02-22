package com.yodimdasiz.yodimdasiz.repository;

import com.yodimdasiz.yodimdasiz.model.FamilyRelations;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FamilyRelationsRepository extends JpaRepository<FamilyRelations, Integer> {
    List<FamilyRelations> findByUserId(Integer userId);
    List<FamilyRelations> findByMemorialId(Integer memorialId);
}
