package com.github.ad.index;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class DataTable implements ApplicationContextAware, PriorityOrdered {

    private static ApplicationContext context;

    private static final Map<Class, IndexAware> dataTableMap = new ConcurrentHashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    @Override
    public int getOrder() {
        return PriorityOrdered.HIGHEST_PRECEDENCE;
    }

    @SuppressWarnings("all")
    private static <T> T getBean(String beanName) {
        return (T) context.getBean(beanName);
    }

    @SuppressWarnings("all")
    private static <T> T getBean(Class clazz) {
        return (T) context.getBean(clazz);
    }

    /**
     * <>usage</>
     *
     * DataTable.of(CreativeUnitIndex.class)
     * DataTable.of(AdUnitIndex.class)
     *
     * @param clazz
     * @param <T>
     * @return
     */
    @SuppressWarnings("all")
    public static <T> T of(Class<T> clazz) {
        T instance = (T) dataTableMap.get(clazz);
        if (instance != null) {
            return instance;
        }
        dataTableMap.put(clazz, getBean(clazz));
        return (T) dataTableMap.get(clazz);
    }
}
