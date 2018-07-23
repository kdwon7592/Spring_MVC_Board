package com.dowon.myboard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.dowon.myboard.dao.ReplyDAO;

public class ReplyWriteCmd implements BoardCmd<Model> {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String rName = request.getParameter("rName");
		String rComment = request.getParameter("rComment");
		String bId = request.getParameter("bId");

		ReplyDAO rDAO = new ReplyDAO();
	
		rDAO.replyWrite(bId, rComment, rName);
	}

}
