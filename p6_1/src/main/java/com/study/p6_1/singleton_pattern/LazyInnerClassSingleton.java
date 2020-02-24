package com.study.p6_1.singleton_pattern;

public class LazyInnerClassSingleton {
    private LazyInnerClassSingleton() {
    }

    public static LazyInnerClassSingleton getInstance() {
        return LazyHolder.lazy;
    }

    private static class LazyHolder {
        private static final LazyInnerClassSingleton lazy = new LazyInnerClassSingleton();
    }
}
