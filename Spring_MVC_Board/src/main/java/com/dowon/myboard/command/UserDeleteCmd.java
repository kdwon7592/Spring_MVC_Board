package com.dowon.myboard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.dowon.myboard.dao.UserDAO;

public class UserDeleteCmd implements BoardCmd<Model> {

	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		String userId = request.getParameter("userId");
		UserDAO dao = new UserDAO();
		dao.delete(userId);
	}
}
