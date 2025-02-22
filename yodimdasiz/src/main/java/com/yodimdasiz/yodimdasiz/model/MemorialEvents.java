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
@Table(name = "memorial_events")
public class MemorialEvents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "memorial_id", nullable = false)
    private MemorialPages memorial;

    @ManyToOne
    @JoinColumn(name = "organizer_id", nullable = false)
    private Users organizer;

    @Column(length = 255, nullable = false)
    private String eventName;

    @Column(columnDefinition = "TEXT")
    private String description;

    private LocalDateTime eventDate;

    @Column(length = 255)
    private String location;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
