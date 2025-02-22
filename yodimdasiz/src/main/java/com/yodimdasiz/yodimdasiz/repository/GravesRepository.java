package com.yodimdasiz.yodimdasiz.repository;

import com.yodimdasiz.yodimdasiz.model.Graves;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GravesRepository extends JpaRepository<Graves, Integer> {
    List<Graves> findByCemeteryId(Integer cemeteryId);
}
