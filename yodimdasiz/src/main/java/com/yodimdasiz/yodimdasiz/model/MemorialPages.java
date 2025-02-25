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
@Table(name = "memorial_pages")
public class MemorialPages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_creator_id", nullable = false)
    private Users userCreatorId;

    private String fullName;

    private String birthDate;

    private String deathDate;

    @Column(columnDefinition = "TEXT")
    private String biography;

    @Column(columnDefinition = "TEXT")
    private String epitaph;

    private String mainPhoto;

    private String qrCode;

    private Integer visitCount;

    @ManyToOne
    @JoinColumn(name = "cemetery_id")
    private Cemeteries cemetery;

    @ManyToOne
    @JoinColumn(name = "maintenance_order_id")
    private MaintenanceOrders maintenanceOrder;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
