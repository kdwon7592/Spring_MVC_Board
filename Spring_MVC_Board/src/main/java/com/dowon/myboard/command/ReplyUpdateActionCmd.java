package com.dowon.myboard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.dowon.myboard.dao.ReplyDAO;

public class ReplyUpdateActionCmd implements BoardCmd<Model> {

	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		String rId = request.getParameter("rId");
		String rComment = request.getParameter("rComment");
		ReplyDAO dao = new ReplyDAO();
		dao.replyUpdate(Integer.parseInt(rId), rComment);
	}

}
