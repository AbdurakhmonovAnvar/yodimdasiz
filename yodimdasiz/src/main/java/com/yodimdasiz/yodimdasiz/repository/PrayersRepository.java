package com.yodimdasiz.yodimdasiz.repository;

import com.yodimdasiz.yodimdasiz.model.Prayers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrayersRepository extends JpaRepository<Prayers, Integer> {
    List<Prayers> findByMemorialId(Integer memorialId);
}
