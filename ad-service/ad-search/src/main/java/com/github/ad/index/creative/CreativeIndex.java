package com.github.ad.index.creative;

import com.github.ad.index.IndexAware;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class CreativeIndex implements IndexAware<Long, CreativeObj> {
    private static Map<Long, CreativeObj> objMap;

    static {
        objMap = new ConcurrentHashMap<>();
    }

    @Override
    public CreativeObj get(Long key) {
        return objMap.get(key);
    }

    @Override
    public void add(Long key, CreativeObj value) {
        log.info("before add: {}", objMap);
        objMap.put(key, value);
        log.info("after add:{}", objMap);
    }

    @Override
    public void update(Long key, CreativeObj value) {
        log.info("before update: {}", objMap);
        CreativeObj creativeObj = objMap.get(key);
        if (creativeObj == null) {
            objMap.put(key, value);
        } else {
            creativeObj.update(value);
        }
        log.info("after update: {}", objMap);
    }

    @Override
    public void delete(Long key, CreativeObj value) {
        log.info("before delete: {}", objMap);
        objMap.remove(key);
        log.info("after delete: {}", objMap);
    }
}
