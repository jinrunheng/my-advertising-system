package com.github.ad.index.district;

import com.github.ad.index.IndexAware;
import com.github.ad.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

@Slf4j
@Component
public class UnitDistrictIndex implements IndexAware<String, Set<Long>> {

    // <province-city,Set<unitId>>
    private static Map<String, Set<Long>> district_unit_map;
    // <unitId,Set<province-city>>
    private static Map<Long, Set<String>> unit_district_map;

    static {
        district_unit_map = new ConcurrentHashMap<>();
        unit_district_map = new ConcurrentHashMap<>();
    }

    @Override
    public Set<Long> get(String key) {
        return district_unit_map.get(key);
    }

    @Override
    @SuppressWarnings("all")
    public void add(String key, Set<Long> value) {
        log.info("UnitDistrictIndex,before add : {}", unit_district_map);
        Set<Long> unitIds = CommonUtils.getOrCreate(
                key, district_unit_map, ConcurrentSkipListSet::new
        );
        unitIds.addAll(value);

        for (Long unitId : value) {
            Set<String> districts = CommonUtils.getOrCreate(
                    unitId, unit_district_map, ConcurrentSkipListSet::new
            );
            districts.add(key);
        }
        log.info("UnitDistrictIndex,after add : {}", unit_district_map);
    }

    @Override
    public void update(String key, Set<Long> value) {
        log.error("district index can not support update");
    }

    @Override
    @SuppressWarnings("all")
    public void delete(String key, Set<Long> value) {
        log.info("UnitDistrictIndex,before delete : {}", unit_district_map);
        Set<Long> unitIds = district_unit_map.get(key);
        if (unitIds != null) {
            unitIds.removeAll(value);
        }

        for (Long unitId : value) {
            Set<String> districtSet = unit_district_map.get(unitId);
            if (districtSet != null) {
                districtSet.remove(key);
            }
        }
        log.info("UnitDistrictIndex,after delete : {}", unit_district_map);
    }
}
