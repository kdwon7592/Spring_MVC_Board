package com.dowon.myboard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.dowon.myboard.dao.BoardDAO;
import com.dowon.myboard.dto.BoardDTO;

public class BoardContentCmd implements BoardCmd {

	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		//request정보를 가져온다.
		
		String bId = request.getParameter("bId");
		//가져온 request로부터 파라미터를 얻는다. list.jsp에서 얻어오는 bId정보.
		
		System.out.println(bId);
		BoardDAO dao = new BoardDAO();
		BoardDTO dto = dao.contentView(Integer.parseInt(bId));
		
		model.addAttribute("content", dto);
		
		
	}

}
