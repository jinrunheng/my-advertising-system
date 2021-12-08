package com.github.ad.dao.unit_condition;

import com.github.ad.entity.unit_condition.AdUnitDistrict;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 完成对 AdUnitDistrict 的增删改查
 */
public interface AdUnitDistrictRepository extends JpaRepository<AdUnitDistrict, Long> {
}
