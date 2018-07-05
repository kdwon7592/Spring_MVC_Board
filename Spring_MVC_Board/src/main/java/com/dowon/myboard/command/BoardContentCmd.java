package com.dowon.myboard.command;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.dowon.myboard.dao.BoardDAO;
import com.dowon.myboard.dao.ReplyDAO;
import com.dowon.myboard.dto.BoardDTO;
import com.dowon.myboard.dto.ReplyDTO;

public class BoardContentCmd implements BoardCmd<Model> {

	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		//request정보를 가져온다.
		
		String bId = request.getParameter("bId");
		//가져온 request로부터 파라미터를 얻는다. list.jsp에서 얻어오는 bId정보.
		System.out.println("Content bId = " + bId);
		
		BoardDAO dao = new BoardDAO();
		ReplyDAO rDAO = new ReplyDAO();
		BoardDTO dto = dao.contentView(Integer.parseInt(bId));
		ArrayList<ReplyDTO>dtos = rDAO.replyView(Integer.parseInt(bId)); //list정보를 받아 list.jsp에 뿌려준다.
		
		dao.increaseHit(Integer.parseInt(bId));
		
		model.addAttribute("reply", dtos);
		model.addAttribute("content", dto);		
	}

}
