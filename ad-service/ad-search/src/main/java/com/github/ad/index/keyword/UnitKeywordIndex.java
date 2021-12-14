package com.github.ad.index.keyword;

import com.github.ad.index.IndexAware;
import com.github.ad.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

@Slf4j
@Component
/**
 *  UnitKeywordIndex 是一个倒排索引
 *  K 用来存储关键字即：keyword
 *  V 用来存储关键字对应的 unitId 的 Set
 */
public class UnitKeywordIndex implements IndexAware<String, Set<Long>> {

    private static Map<String, Set<Long>> keyword_unit_map;
    private static Map<Long, Set<String>> unit_keyword_map;

    static {
        keyword_unit_map = new ConcurrentHashMap<>();
        unit_keyword_map = new ConcurrentHashMap<>();
    }

    @Override
    public Set<Long> get(String key) {
        if (key == null) {
            return Collections.emptySet();
        }
        Set<Long> unitIds = keyword_unit_map.get(key);
        if (unitIds == null) {
            return Collections.emptySet();
        }
        return unitIds;
    }

    @Override
    public void add(String key, Set<Long> value) {
        // 更新 keyword_unit_map
        log.info("UnitKeywordIndex,before add : {}", unit_keyword_map);
//        Set<Long> unitIds = keyword_unit_map.get(key);
//        if (unitIds == null) {
//            unitIds = new ConcurrentSkipListSet<>();
//        }
        Set<Long> unitIds = CommonUtils.getOrCreate(
                key, keyword_unit_map, ConcurrentSkipListSet::new
        );
        unitIds.addAll(value);

        // 更新 unit_keyword_map
        for (Long unitId : value) {
//            Set<String> keywordSet = unit_keyword_map.get(unitId);
//            if (keywordSet == null) {
//                keywordSet = new ConcurrentSkipListSet<>();
//            }
            Set<String> keywordSet = CommonUtils.getOrCreate(
                    unitId, unit_keyword_map, ConcurrentSkipListSet::new
            );
            keywordSet.add(key);
        }
        log.info("UnitKeywordIndex,after add : {}", unit_keyword_map);
    }

    @Override
    public void update(String key, Set<Long> value) {
        log.info("keyword index can not support update");
    }

    @Override
    public void delete(String key, Set<Long> value) {
        log.info("UnitKeywordIndex,before delete : {}", unit_keyword_map);
        Set<Long> unitIds = keyword_unit_map.get(key);
        if (unitIds != null) {
            unitIds.removeAll(value);
        }

        for (Long unitId : value) {
            Set<String> keywordSet = unit_keyword_map.get(unitId);
            if (keywordSet != null) {
                keywordSet.remove(key);
            }
        }
        log.info("UnitKeywordIndex,before delete : {}", unit_keyword_map);
    }

    /**
     * 查看某个推广单元（unitId）是否包含了这些关键词
     *
     * @param unitId
     * @param keywords
     * @return
     */
    public boolean match(Long unitId, List<String> keywords) {
        if (unit_keyword_map.containsKey(unitId)
                && CollectionUtils.isNotEmpty(unit_keyword_map.get(unitId))) {
            return unit_keyword_map.get(unitId).containsAll(keywords);
        }
        return false;
    }
}
