package com.dowon.myboard.command;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.dowon.myboard.dao.ReplyDAO;
import com.dowon.myboard.dto.ReplyDTO;

public class ReplyUpdateCmd implements BoardCmd<Model>{
	
	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		//request정보를 가져온다.
		
		String rId = request.getParameter("rId");
		//가져온 request로부터 파라미터를 얻는다. list.jsp에서 얻어오는 bId정보.
		System.out.println("rid : " + rId);
		ReplyDAO dao = new ReplyDAO();
		ReplyDTO dto = dao.replyGet(Integer.parseInt(rId));
		
		model.addAttribute("reply_update", dto);
		
	}

}
