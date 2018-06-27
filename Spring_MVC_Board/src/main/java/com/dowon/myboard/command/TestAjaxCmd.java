package com.dowon.myboard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

public class TestAjaxCmd implements BoardCmd<ModelMap>{

	@Override
	public void execute(ModelMap model) {
		// TODO Auto-generated method stub
		Map<String, Object> json = model;

		HttpServletRequest request = (HttpServletRequest) json.get("request");

		String t1 = request.getParameter("test1");
		String t2 = request.getParameter("test2");
		
		System.out.println("t1 : " + t1 + ",t2 : " + t2);
		
		model.addAttribute("test1", t1 + "add test");
		model.addAttribute("test2", t2 + "add test 2");	
	}

}
