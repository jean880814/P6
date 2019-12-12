package com.study.p6_1.P61_2_1to1_2_2;

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
