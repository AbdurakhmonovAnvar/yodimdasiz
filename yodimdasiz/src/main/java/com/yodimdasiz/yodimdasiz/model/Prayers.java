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
@Table(name = "prayers")
public class Prayers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "memorial_id", nullable = false)
    private MemorialPages memorial;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @Column(length = 100, nullable = false)
    private String prayerType;

    @CreationTimestamp
    private LocalDateTime prayedAt;
}
