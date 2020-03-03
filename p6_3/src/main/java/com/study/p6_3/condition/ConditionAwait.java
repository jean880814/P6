package com.study.p6_3.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ConditionAwait implements Runnable {
    private Lock lock;
    private Condition condition;

    public ConditionAwait(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        try {
            lock.lock();
            System.out.println("before await" + Thread.currentThread().getName());
            condition.await();
            System.out.println("after await" + Thread.currentThread().getName());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
