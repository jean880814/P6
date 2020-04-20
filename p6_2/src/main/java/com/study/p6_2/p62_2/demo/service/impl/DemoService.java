package com.study.p6_2.p62_2.demo.service.impl;

import com.study.p6_2.p62_2.demo.service.IDemoService;
import com.study.p6_2.p62_2.demo.service.IQueryService;
import com.study.p6_2.p62_2.mvcframework.annotation.MyAutowired;
import com.study.p6_2.p62_2.mvcframework.annotation.MyService;

/**
 * 核心业务逻辑
 */
@MyService
public class DemoService implements IDemoService {

	@MyAutowired
	private IQueryService iQueryService;

	public String get(String name) {
//		iQueryService.query(name);
		return "My name is " + name;
	}

}
