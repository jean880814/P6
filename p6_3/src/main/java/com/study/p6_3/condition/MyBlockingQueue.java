package com.study.p6_3.condition;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyBlockingQueue<T> {
    private final Lock lock = new ReentrantLock();
    private final Condition emptyCondition = lock.newCondition();
    private final Condition fullCondition = lock.newCondition();
    private static final int DEFAULT_INITIAL_CAPACITY = 11;
    private final List<T> q;
    private volatile int size = 0;
    private static int fullsize;

    public MyBlockingQueue() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public MyBlockingQueue(int capacity) {
        fullsize = capacity;
        this.q = new ArrayList<T>(capacity);
    }

    public T take() {
        try {
            lock.lock();
            if (q.isEmpty()) {
                emptyCondition.await();
            }
            try {
                size--;
                return q.get(0);
            } finally {
                q.remove(0);
                System.out.println(Thread.currentThread().getName() + " after take q = " + q);
                fullCondition.signal();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return null;
    }

    public void put(T t) {
        try {
            lock.lock();
            if (size == fullsize - 1) {
                fullCondition.await();
            }
            q.add(size ++, t);
            System.out.println(Thread.currentThread().getName() + " after put q = " + q);
            emptyCondition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
