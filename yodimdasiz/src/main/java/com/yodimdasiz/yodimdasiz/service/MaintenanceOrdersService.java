package com.yodimdasiz.yodimdasiz.service;

import com.yodimdasiz.yodimdasiz.model.MaintenanceOrders;
import com.yodimdasiz.yodimdasiz.repository.MaintenanceOrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaintenanceOrdersService {
    private final MaintenanceOrdersRepository maintenanceOrdersRepository;

    public MaintenanceOrders createOrder(MaintenanceOrders order) {
        return maintenanceOrdersRepository.save(order);
    }

    public List<MaintenanceOrders> getAllOrders() {
        return maintenanceOrdersRepository.findAll();
    }

    public MaintenanceOrders getOrderById(Integer id) {
        return maintenanceOrdersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Transactional
    public MaintenanceOrders updateOrder(Integer id, MaintenanceOrders updatedOrder) {
        MaintenanceOrders existingOrder = getOrderById(id);
        existingOrder.setGrave(updatedOrder.getGrave());
        existingOrder.setUser(updatedOrder.getUser());
        existingOrder.setServiceType(updatedOrder.getServiceType());
        existingOrder.setPrice(updatedOrder.getPrice());
        existingOrder.setStatus(updatedOrder.getStatus());
        return maintenanceOrdersRepository.save(existingOrder);
    }

    public void deleteOrder(Integer id) {
        MaintenanceOrders order = getOrderById(id);
        maintenanceOrdersRepository.delete(order);
    }
}
