package com.study.p6_3;

import com.study.p6_3.rabbitmq.springboot.provider.MyProvider;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class P63ApplicationTests {

    @Autowired
    MyProvider provider;

    @Test
    public void contextLoads() {
        provider.send();
    }

}
