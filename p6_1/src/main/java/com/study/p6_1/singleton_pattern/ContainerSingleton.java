package com.study.p6_1.singleton_pattern;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

public class ContainerSingleton {
    private static Map<Class<?>, AtomicReference<Object>> map = new ConcurrentHashMap<Class<?>, AtomicReference<Object>>();
    private static Map<Class<?>, Object> omap = new ConcurrentHashMap<Class<?>, Object>();

    private ContainerSingleton() {
    }

    public static Object getInstanceByAomic(Class<?> clazz) throws IllegalAccessException, InstantiationException {
        AtomicReference<Object> obj = map.get(clazz);
        if (obj == null) {
            AtomicReference<Object> newObj = new AtomicReference<Object>(clazz.newInstance());
            obj = map.putIfAbsent(clazz, newObj);
            if (obj == null) {
                obj = newObj;
            }
        }
        return obj.get();
    }

    public static Object getInstance(Class<?> clazz) throws IllegalAccessException, InstantiationException {
        synchronized (omap) {
            if (!omap.containsKey(clazz)) {
                Object obj = clazz.newInstance();
                omap.put(clazz, obj);
                return obj;
            } else {
                return omap.get(clazz);
            }
        }
    }
}
