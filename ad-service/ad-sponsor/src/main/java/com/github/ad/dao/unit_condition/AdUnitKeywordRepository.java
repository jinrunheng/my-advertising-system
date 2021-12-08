package com.github.ad.dao.unit_condition;

import com.github.ad.entity.unit_condition.AdUnitKeyword;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 完成对 AdUnitKeyword 的增删改查
 */
public interface AdUnitKeywordRepository extends JpaRepository<AdUnitKeyword, Long> {
}
