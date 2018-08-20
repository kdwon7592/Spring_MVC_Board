package com.dowon.myboard.command;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.dowon.myboard.dao.BoardDAO;
import com.dowon.myboard.dto.BoardDTO;
import com.dowon.myboard.util.PagingList;

public class BoardMainCmd implements BoardCmd<Model> {

	@Override
	public void execute(Model model) {

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		BoardDAO dao = new BoardDAO();
			
		ArrayList<BoardDTO> dtos = dao.MainList();
		model.addAttribute("list", dtos);
	}
}
