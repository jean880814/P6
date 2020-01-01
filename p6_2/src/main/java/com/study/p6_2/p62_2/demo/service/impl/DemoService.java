package com.study.p6_2.p62_2.demo.service.impl;

import com.study.p6_2.p62_2.demo.service.IDemoService;
import com.study.p6_2.p62_2.mvcframework.annotation.MyService;

/**
 * 核心业务逻辑
 */
@MyService
public class DemoService implements IDemoService {

	public String get(String name) {
		return "My name is " + name;
	}

}
