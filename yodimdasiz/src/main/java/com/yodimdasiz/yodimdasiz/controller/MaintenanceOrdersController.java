package com.yodimdasiz.yodimdasiz.controller;

import com.yodimdasiz.yodimdasiz.model.MaintenanceOrders;
import com.yodimdasiz.yodimdasiz.service.MaintenanceOrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maintenance-orders")
@RequiredArgsConstructor
public class MaintenanceOrdersController {
    private final MaintenanceOrdersService maintenanceOrdersService;

    @PostMapping
    public ResponseEntity<MaintenanceOrders> createOrder(@RequestBody MaintenanceOrders order) {
        return ResponseEntity.ok(maintenanceOrdersService.createOrder(order));
    }

    @GetMapping
    public ResponseEntity<List<MaintenanceOrders>> getAllOrders() {
        return ResponseEntity.ok(maintenanceOrdersService.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaintenanceOrders> getOrderById(@PathVariable Integer id) {
        return ResponseEntity.ok(maintenanceOrdersService.getOrderById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaintenanceOrders> updateOrder(@PathVariable Integer id, @RequestBody MaintenanceOrders updatedOrder) {
        return ResponseEntity.ok(maintenanceOrdersService.updateOrder(id, updatedOrder));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer id) {
        maintenanceOrdersService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
