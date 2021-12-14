package com.github.ad.index.interest;

import com.github.ad.index.IndexAware;
import com.github.ad.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

@Slf4j
@Component
public class UnitItIndex implements IndexAware<String, Set<Long>> {

    // <itTag,Set<unitId>>
    private static Map<String, Set<Long>> it_unit_map;
    // <unitId,Set<itTag>>
    private static Map<Long, Set<String>> unit_it_map;

    static {
        it_unit_map = new ConcurrentHashMap<>();
        unit_it_map = new ConcurrentHashMap<>();
    }

    @Override
    public Set<Long> get(String key) {
        return it_unit_map.get(key);
    }

    @Override
    @SuppressWarnings("all")
    public void add(String key, Set<Long> value) {
        log.info("UnitItIndex,before add : {}", unit_it_map);
        Set<Long> unitIds = CommonUtils.getOrCreate(
                key, it_unit_map, ConcurrentSkipListSet::new
        );
        unitIds.addAll(value);

        for (Long unitId : value) {
            Set<String> its = CommonUtils.getOrCreate(
                    unitId, unit_it_map, ConcurrentSkipListSet::new
            );
            its.add(key);
        }
        log.info("UnitItIndex,after add : {}", unit_it_map);
    }

    @Override
    public void update(String key, Set<Long> value) {
        log.error("it index can not support update");
    }

    @Override
    @SuppressWarnings("all")
    public void delete(String key, Set<Long> value) {
        log.info("UnitItIndex,before delete : {}", unit_it_map);
        Set<Long> unitIds = it_unit_map.get(key);
        if (unitIds != null) {
            unitIds.removeAll(value);
        }

        for (Long unitId : value) {
            Set<String> itTagSet = unit_it_map.get(unitId);
            if (itTagSet != null) {
                itTagSet.remove(key);
            }
        }
        log.info("UnitItIndex,after delete : {}", unit_it_map);
    }

    public boolean match(Long unitId, List<String> itTags) {
        if (unit_it_map.containsKey(unitId)
                && CollectionUtils.isNotEmpty(unit_it_map.get(unitId))) {
            return unit_it_map.get(unitId).containsAll(itTags);
        }
        return false;
    }
}
