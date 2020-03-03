package com.study.p6_3.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {
    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        Condition condition1 =  lock.newCondition();
        new Thread(new ConditionAwait(lock, condition)).start();
        Thread.sleep(1000);
        new Thread(new ConditionNotify(lock, condition)).start();
    }
}
