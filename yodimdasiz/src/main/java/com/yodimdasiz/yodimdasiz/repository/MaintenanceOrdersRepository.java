package com.yodimdasiz.yodimdasiz.repository;

import com.yodimdasiz.yodimdasiz.model.MaintenanceOrders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaintenanceOrdersRepository extends JpaRepository<MaintenanceOrders, Integer> {
    List<MaintenanceOrders> findByUserId(Integer userId);
    List<MaintenanceOrders> findByStatus(com.yodimdasiz.yodimdasiz.enums.OrderStatus status);
}
