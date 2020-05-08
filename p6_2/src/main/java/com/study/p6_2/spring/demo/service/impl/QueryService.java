package com.study.p6_2.spring.demo.service.impl;

import com.study.p6_2.spring.demo.service.IDemoService;
import com.study.p6_2.spring.demo.service.IQueryService;
import com.study.p6_2.spring.mvcframework.annotation.MyAutowired;
import com.study.p6_2.spring.mvcframework.annotation.MyService;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 查询业务
 * @author Tom
 *
 */
@MyService
@Slf4j
public class QueryService implements IQueryService {
	@MyAutowired
	private IDemoService iDemoService;

	/**
	 * 查询
	 */
	public String query(String name) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(new Date());
		String json = "{name:\"" + name + "\",time:\"" + time + "\"}";
		log.info("这是在业务方法中打印的：" + json);
		return json;
	}

}
