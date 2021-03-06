package com.study.p6_3.blocking_queue;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class OrderProcessor implements Runnable,IProcessor{
    private static BlockingDeque<DoProcessor> queue = new LinkedBlockingDeque<>();

    private final IProcessor nextProcessor;
    private volatile boolean isFinished = false;

    public OrderProcessor(IProcessor nextProcessor) {
        this.nextProcessor = nextProcessor;
    }

    @Override
    public void run() {
        while (!isFinished) {
            try {
                DoProcessor processor = queue.take();
                System.out.println("creat order");
                nextProcessor.processor(processor);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutdown(){
        this.isFinished = true;
    }

    @Override
    public void processor(DoProcessor dProcessor) {
        queue.add(dProcessor);
    }

}
