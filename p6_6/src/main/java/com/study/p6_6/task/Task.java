package com.study.p6_6.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @program: P6
 * @author: Jean
 * @create: 2022-07-08 15:24
 */
@Service
public class Task {

    @Scheduled(fixedRate = 10)
    public void test() {
        System.out.println("test");
    }
}
