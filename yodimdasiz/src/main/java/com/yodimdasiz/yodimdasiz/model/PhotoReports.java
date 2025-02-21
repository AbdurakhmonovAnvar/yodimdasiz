package com.yodimdasiz.yodimdasiz.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "photo_reports")
public class PhotoReports {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "maintenance_order_id", nullable = false)
    private MaintenanceOrders maintenanceOrder;

    @Column(length = 255, nullable = false)
    private String photoUrl;

    @Column(columnDefinition = "TEXT")
    private String description;

    @CreationTimestamp
    private LocalDateTime uploadedAt;
}
