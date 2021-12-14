package com.github.ad.index.adplan;

import com.github.ad.index.IndexAware;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class AdPlanIndex implements IndexAware<Long, AdPlanObj> {

    private static Map<Long, AdPlanObj> objMap;

    static {
        objMap = new ConcurrentHashMap<>();
    }

    @Override
    public AdPlanObj get(Long key) {
        return objMap.get(key);
    }

    @Override
    public void add(Long key, AdPlanObj value) {
        log.info("before add : {}", objMap);
        objMap.put(key, value);
        log.info("after add : {}", objMap);
    }

    @Override
    public void update(Long key, AdPlanObj value) {
        log.info("before update : {}", objMap);
        AdPlanObj adPlanObj = objMap.get(key);
        if (adPlanObj == null) {
            objMap.put(key, value);
        } else {
            adPlanObj.update(value);
        }
        log.info("after update : {}", objMap);
    }

    @Override
    public void delete(Long key, AdPlanObj value) {
        log.info("before delete : {}", objMap);
        objMap.remove(key);
        log.info("after delete : {}", objMap);
    }
}
