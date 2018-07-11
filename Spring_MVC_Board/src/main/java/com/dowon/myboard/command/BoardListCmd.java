package com.dowon.myboard.command;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.dowon.myboard.dao.BoardDAO;
import com.dowon.myboard.dto.BoardDTO;
import com.dowon.myboard.util.PagingList;

public class BoardListCmd implements BoardCmd<Model> {

	@Override
	public void execute(Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		int currentPage = 1;
		int maxList = 10;
		
		if(request.getParameter("pages")!=null) {
			currentPage = Integer.parseInt(request.getParameter("pages"));
			System.out.println("Testpages: " + request.getParameter("pages"));

		}
//		
		PagingList pg = new PagingList(currentPage, maxList);
//		
//		int offset = (pg.getCurrentPage() - 1) * pg.getMaxList();
		
		int offset = (currentPage - 1) * maxList ;
		
		BoardDAO dao = new BoardDAO();
		ArrayList<BoardDTO>dtos = dao.list(offset, maxList); //list정보를 받아 list.jsp에 뿌려준다.
		pg.setTotal_num(dao.total_cnt());
		
		pg.makePaging();
		System.out.println(pg.toString());
		
		model.addAttribute("list", dtos);
		model.addAttribute("paging", pg);
	}
}
