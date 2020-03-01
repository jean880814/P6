package com.study.p6_1.singleton_pattern;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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
        Lock lock = new ReentrantLock();
        lock.lock();
        lock.unlock();
        return local.get();
    }
}
