package com.github.ad.index.adunit;

import com.github.ad.index.IndexAware;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class AdUnitIndex implements IndexAware<Long, AdUnitObj> {

    private static Map<Long, AdUnitObj> objMap;

    static {
        objMap = new ConcurrentHashMap<>();
    }

    @Override
    public AdUnitObj get(Long key) {
        return objMap.get(key);
    }

    @Override
    public void add(Long key, AdUnitObj value) {
        log.info("before add : {}", objMap);
        objMap.put(key, value);
        log.info("after add : {}", objMap);
    }

    @Override
    public void update(Long key, AdUnitObj value) {
        log.info("before update : {}", objMap);
        AdUnitObj adUnitObj = objMap.get(key);
        if (adUnitObj == null) {
            objMap.put(key, value);
        } else {
            adUnitObj.update(value);
        }
        log.info("after update : {}", objMap);
    }

    @Override
    public void delete(Long key, AdUnitObj value) {
        log.info("before delete : {}", objMap);
        objMap.remove(key);
        log.info("after delete : {}", objMap);
    }
}
