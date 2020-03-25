package com.study.p6_3.springboot_starter;

import com.study.testspringbootstarter.service.FormatTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FormatController {
    @Autowired
    FormatTemplate formatTemplate;

    @GetMapping("/format")
    public String format(){
        User user = new User();
        user.setAge(1);
        user.setName("Jean");
        return formatTemplate.doFormat(user);
    }
}
