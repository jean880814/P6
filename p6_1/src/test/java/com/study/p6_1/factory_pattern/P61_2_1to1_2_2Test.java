package com.study.p6_1.factory_pattern;

import com.study.p6_1.factory_pattern.car.Car;
import com.study.p6_1.P61_2_1to1_2_2.EnumSingleton;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class P61_2_1to1_2_2Test {

    @Test
    void EnumSingletonTest() {
        Car car1 = EnumSingleton.ENUM_SINGLETON.getCar();
        Car car2 = EnumSingleton.ENUM_SINGLETON.getCar();
        System.out.println(car1 == car2);
    }
}
