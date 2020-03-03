package com.study.p6_3.condition;

public class MyBlockingQueueTest {
    public static void main(String[] args) throws InterruptedException {
        MyBlockingQueue<Integer> queue = new MyBlockingQueue<Integer>();
//        for (int i = 0; i < 20; i++) {
//            int finalI = i;
//            new Thread(() -> queue.put(finalI), "put_thread" + i).start();
//        }
//        Thread.sleep(2000);
        for (int i = 0; i < 20; i++) {
            new Thread(()-> System.out.println(queue.take()),"take_thread" + i).start();
        }
    }
}
