package com.study.p6_3.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ConditionNotify implements Runnable {
    private Lock lock;
    private Condition condition;

    public ConditionNotify(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        try {
            lock.lock();
            System.out.println("before notify" + Thread.currentThread().getName());
            condition.signal();
            System.out.println("after notify" + Thread.currentThread().getName());
        } finally {
            lock.unlock();
        }
    }
}
