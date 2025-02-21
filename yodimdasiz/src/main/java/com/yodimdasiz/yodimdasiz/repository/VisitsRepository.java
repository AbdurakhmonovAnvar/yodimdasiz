package com.yodimdasiz.yodimdasiz.repository;

import com.yodimdasiz.yodimdasiz.model.Visits;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VisitsRepository extends JpaRepository<Visits, Integer> {
    List<Visits> findByUserId(Integer userId);
    List<Visits> findByMemorialId(Integer memorialId);
}
