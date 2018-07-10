package com.dowon.myboard.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.dowon.myboard.dao.BoardDAO;
import com.dowon.myboard.dto.BoardDTO;

public class BoardListCmd implements BoardCmd<Model> {

	@Override
	public void execute(Model model) {

		BoardDAO dao = new BoardDAO();
		ArrayList<BoardDTO>dtos = dao.list(); //list정보를 받아 list.jsp에 뿌려준다.
		model.addAttribute("list", dtos);
	}
}
