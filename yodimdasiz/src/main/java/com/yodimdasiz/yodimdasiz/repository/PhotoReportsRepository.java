package com.yodimdasiz.yodimdasiz.repository;

import com.yodimdasiz.yodimdasiz.model.PhotoReports;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoReportsRepository extends JpaRepository<PhotoReports, Integer> {
    List<PhotoReports> findByMaintenanceOrderId(Integer maintenanceOrderId);
}
