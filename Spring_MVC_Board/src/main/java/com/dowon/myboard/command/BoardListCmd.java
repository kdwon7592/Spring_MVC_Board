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
		
		// 페이징 쿼리를 위한 변수
		int currentPage;
		int maxList;
		
		if(request.getParameter("pages")!=null) {
			currentPage = Integer.parseInt(request.getParameter("pages"));
		}else {
			 currentPage = 1;
		}
//		
		if(request.getParameter("maxListExtend") != null) {
			maxList = Integer.parseInt(request.getParameter("maxListExtend"));
		}else {
			maxList = 30;
		}
		
		PagingList pg = new PagingList(currentPage, maxList);
//		
//		int offset = (pg.getCurrentPage() - 1) * pg.getMaxList();
		
		int offset = (currentPage - 1) * maxList ;
		
		BoardDAO dao = new BoardDAO();
		ArrayList<BoardDTO>dtos = dao.list(offset, maxList); //list정보를 받아 list.jsp에 뿌려준다.
		pg.setTotal_num(dao.total_cnt());
		
		pg.makePaging();
		
		model.addAttribute("list", dtos);
		model.addAttribute("paging", pg);
	}
}
