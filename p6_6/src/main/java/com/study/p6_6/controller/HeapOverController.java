package com.study.p6_6.controller;

import com.study.p6_6.p6_6_1_2.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class HeapOverController {
    private static List<Person> personList = new ArrayList<Person>();
    @RequestMapping("/over")
    public String over() throws InterruptedException {
        while (true) {
            personList.add(new Person());
            Thread.sleep(10);
        }
    }
}
