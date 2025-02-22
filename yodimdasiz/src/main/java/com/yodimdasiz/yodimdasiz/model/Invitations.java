package com.yodimdasiz.yodimdasiz.model;

import com.yodimdasiz.yodimdasiz.enums.InvitationStatus;
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
@Table(name = "invitations")
public class Invitations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "memorial_event_id", nullable = false)
    private MemorialEvents memorialEvent;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private InvitationStatus status;

    @CreationTimestamp
    private LocalDateTime sentAt;
}
