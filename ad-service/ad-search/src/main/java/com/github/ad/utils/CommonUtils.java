package com.github.ad.utils;

import java.util.Map;
import java.util.function.Supplier;

public class CommonUtils {

    public static <K, V> V getOrCreate(K key, Map<K, V> map, Supplier<V> factory) {
        // 如果 map 中存在 key 则返回，如果不存在则返回 factory.get()
        return map.computeIfAbsent(key, k -> factory.get());
    }
}
