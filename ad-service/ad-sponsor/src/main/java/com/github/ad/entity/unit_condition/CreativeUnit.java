package com.github.ad.entity.unit_condition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * 创意与推广单元关联表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "creative_unit")
public class CreativeUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "creative_id", nullable = false)
    private Long creativeId;

    @Column(name = "unit_id", nullable = false)
    private Long unitId;

    public CreativeUnit(Long creativeId, Long unitId) {
        this.creativeId = creativeId;
        this.unitId = unitId;
    }
}
