package com.study.p6_1.singleton_pattern;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ContainerSingleton {
    private static Map<Class<?>, Object> map = new ConcurrentHashMap<Class<?>, Object>();

    private ContainerSingleton() {
    }

    public static ContainerSingleton getInstance(Class<?> clazz) throws IllegalAccessException, InstantiationException {
        synchronized (map) {
            if (!map.containsKey(clazz)) {
                Object obj = clazz.newInstance();
                map.put(clazz, obj);
                return (ContainerSingleton) obj;
            } else {
                return (ContainerSingleton) map.get(clazz);
            }
        }
    }
}
