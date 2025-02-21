package com.yodimdasiz.yodimdasiz.model;

import com.yodimdasiz.yodimdasiz.enums.RelationType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "family_relations")
public class FamilyRelations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "memorial_id", nullable = false)
    private MemorialPages memorial;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private RelationType relationType;
}
