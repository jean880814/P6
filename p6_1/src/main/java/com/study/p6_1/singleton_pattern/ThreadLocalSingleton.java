package com.study.p6_1.singleton_pattern;

public  class ThreadLocalSingleton {
    private static final ThreadLocal<ThreadLocalSingleton> local = new ThreadLocal<ThreadLocalSingleton>();
    private ThreadLocalSingleton(){

    }

    public static ThreadLocalSingleton getInstance(){
        ThreadLocalSingleton threadLocalSingleton = local.get();
        if (threadLocalSingleton == null) {
            threadLocalSingleton = new ThreadLocalSingleton();
            local.set(threadLocalSingleton);
        }
        return threadLocalSingleton;
    }

    private Object readResolve(){
        return local.get();
    }
}
