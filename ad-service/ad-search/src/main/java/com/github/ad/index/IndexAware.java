package com.github.ad.index;

/**
 * 关于 IndexAware 的命名：
 * <p>
 * 在 Spring 源码中，有大量的 XXXAware 接口，让 Bean 向容器表明它们需要某中基础设施依赖。
 * <p>
 * 譬如 ApplicationContextAware，如果实现了这个接口，就是告诉 Spring ，我想要获取 ApplicationContext 的信息
 * <p>
 * IndexAware 接口用于获取索引，并实现对索引的增删改查
 *
 * @param <K>
 * @param <V>
 */
public interface IndexAware<K, V> {

    V get(K key);

    void add(K key, V value);

    void update(K key, V value);

    void delete(K key, V value);
}
