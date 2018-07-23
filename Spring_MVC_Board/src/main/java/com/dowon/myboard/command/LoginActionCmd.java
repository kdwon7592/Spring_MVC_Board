package com.dowon.myboard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.dowon.myboard.dao.UserDAO;

public class LoginActionCmd implements BoardCmd<Model>{
	
	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String userId = request.getParameter("userId");
		String userPassword = request.getParameter("userPassword");
			
		UserDAO userDao = new UserDAO();
		int result = userDao.login(userId, userPassword);
		//System.out.println(user.getUserId() + user.getUserPassword());
		if(result == 1){
			request.setAttribute("result", 1);
			request.getSession(true).setAttribute("User", userId);;
		} else if(result == 0){
			request.setAttribute("result", 0);
		} else if(result == -1){
			request.setAttribute("result", -1);
		}
	}
}
