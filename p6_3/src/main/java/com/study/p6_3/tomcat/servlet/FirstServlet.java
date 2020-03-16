package com.study.p6_3.tomcat.servlet;

import com.study.p6_3.tomcat.http.NRequest;
import com.study.p6_3.tomcat.http.NResponse;
import com.study.p6_3.tomcat.http.NServlet;

public class FirstServlet extends NServlet {

	public void doGet(NRequest request, NResponse response) throws Exception {
		this.doPost(request, response);
	}

	public void doPost(NRequest request, NResponse response) throws Exception {
		response.write("This is First Serlvet");
	}

}
