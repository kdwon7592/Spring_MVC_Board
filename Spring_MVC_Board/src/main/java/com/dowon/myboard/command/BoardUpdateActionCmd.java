package com.dowon.myboard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.dowon.myboard.dao.BoardDAO;

public class BoardUpdateActionCmd implements BoardCmd<Model> {

	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		String bId = request.getParameter("bId");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		BoardDAO dao = new BoardDAO();
		dao.update(Integer.parseInt(bId), bContent, bTitle);
	}

}
