package com.github.ad.index.creativeunit;

import com.github.ad.index.IndexAware;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

@Slf4j
@Component
public class CreativeUnitIndex implements IndexAware<String, CreativeUnitObj> {

    // <adId-unitId,CreativeUnitObj>
    private static Map<String, CreativeUnitObj> objMap;

    // <adId,Set<unitId>>
    private static Map<Long, Set<Long>> creative_unit_map;

    // <unitId,Set<adId>>
    private static Map<Long, Set<Long>> unit_creative_map;

    static {
        objMap = new ConcurrentHashMap<>();
        creative_unit_map = new ConcurrentHashMap<>();
        unit_creative_map = new ConcurrentHashMap<>();
    }

    @Override
    public CreativeUnitObj get(String key) {
        return objMap.get(key);
    }

    @Override
    public void add(String key, CreativeUnitObj value) {
        log.info("before add: {}", objMap);
        objMap.put(key, value);

        Set<Long> unitIdSet = creative_unit_map.get(value.getAdId());
        if (CollectionUtils.isEmpty(unitIdSet)) {
            unitIdSet = new ConcurrentSkipListSet<>();
        }
        unitIdSet.add(value.getUnitId());
        creative_unit_map.put(value.getAdId(), unitIdSet);

        Set<Long> creativeIdSet = unit_creative_map.get(value.getUnitId());
        if (CollectionUtils.isEmpty(creativeIdSet)) {
            creativeIdSet = new ConcurrentSkipListSet<>();
        }
        creativeIdSet.add(value.getUnitId());
        unit_creative_map.put(value.getUnitId(), creativeIdSet);

        log.info("after add: {}", objMap);
    }

    @Override
    public void update(String key, CreativeUnitObj value) {
        log.error("CreativeUnitIndex not support update");
    }

    @Override
    public void delete(String key, CreativeUnitObj value) {
        log.info("before delete: {}", objMap);

        objMap.remove(key);

        Set<Long> unitIdSet = creative_unit_map.get(value.getAdId());
        if (CollectionUtils.isNotEmpty(unitIdSet)) {
            unitIdSet.remove(value.getUnitId());
        }

        Set<Long> creativeIdSet = unit_creative_map.get(value.getUnitId());
        if (CollectionUtils.isNotEmpty(creativeIdSet)) {
            creativeIdSet.remove(value.getAdId());
        }
        log.info("after delete: {}", objMap);
    }
}
