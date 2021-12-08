package com.github.ad.dao.unit_condition;

import com.github.ad.entity.unit_condition.CreativeUnit;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 完成对 CreativeUnit 的增删改查
 */
public interface CreativeUnitRepository extends JpaRepository<CreativeUnit, Long> {
}
