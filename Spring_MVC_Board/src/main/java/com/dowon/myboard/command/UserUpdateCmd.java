package com.dowon.myboard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.dowon.myboard.dao.UserDAO;
import com.dowon.myboard.dto.UserDTO;

public class UserUpdateCmd implements BoardCmd<Model>{
	
	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		//request정보를 가져온다.
		
		String userId = request.getParameter("userId");
		//가져온 request로부터 파라미터를 얻는다.
		
		UserDAO dao = new UserDAO();
		UserDTO dto = dao.getUser(userId);
		
		System.out.println(dto.getUserId());
		
		model.addAttribute("user", dto);
	}
}
