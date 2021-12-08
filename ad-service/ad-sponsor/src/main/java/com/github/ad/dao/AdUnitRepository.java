package com.github.ad.dao;

import com.github.ad.entity.AdUnit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 完成对 AdUser 的增删改查
 */
public interface AdUnitRepository extends JpaRepository<AdUnit, Long> {
    AdUnit findByPlanIdAndUnitName(Long planId, String unitName);

    List<AdUnit> findAllByUnitStatus(Integer unitStatus);
}
