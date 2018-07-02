package com.dowon.myboard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import com.dowon.myboard.dao.ReplyDAO;

public class ReplyWriteCmd implements BoardCmd<ModelMap> {

	@Override
	public void execute(ModelMap model) {
		// TODO Auto-generated method stub
		Map<String, Object> json = model;

		HttpServletRequest request = (HttpServletRequest) json.get("request");

		String rName = request.getParameter("rName");
		String rComment = request.getParameter("rComment");
		String bId = request.getParameter("bId");
		System.out.println(rName + " " + rComment + " CMD " + bId + " ");
		ReplyDAO rDAO = new ReplyDAO();
		rDAO.replyWrite(bId, rComment, rName);
		System.out.println("완료?");
	}

}
