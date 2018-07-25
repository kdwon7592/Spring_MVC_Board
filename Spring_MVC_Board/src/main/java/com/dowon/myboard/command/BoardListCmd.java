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
		
		String opt = request.getParameter("opt"); //검색 유형
		String condition = request.getParameter("cond"); //검색 조건
		
		BoardDAO dao = new BoardDAO();
			
		// 페이징 쿼리를 위한 변수
		int currentPage;
		int maxList;

		if (request.getParameter("pages") != null) {
			currentPage = Integer.parseInt(request.getParameter("pages"));
		} else {
			currentPage = 1;
		}
		
		if (request.getParameter("maxList") != null) {
			maxList = Integer.parseInt(request.getParameter("maxList"));
		} else {
			if(request.getSession().getAttribute("maxList") != null) {
				maxList = (Integer) request.getSession().getAttribute("maxList");
			}else {
			maxList = 30;
			}
		}

		PagingList pg = new PagingList(currentPage, maxList);
		//
		// int offset = (pg.getCurrentPage() - 1) * pg.getMaxList();

		int offset = (currentPage - 1) * maxList;

		
		ArrayList<BoardDTO> dtos = dao.list(offset, maxList, opt, condition); // list정보를 받아 list.jsp에 뿌려준다.
		pg.setTotal_num(dao.total_cnt(opt, condition));

		pg.makePaging();

		model.addAttribute("list", dtos);
		model.addAttribute("paging", pg);
		//세션에 검색정보를 담아 계속해서 검색정보를 유지한다.
		request.getSession().setAttribute("opt", opt);
		request.getSession().setAttribute("cond", condition);
		request.getSession().setAttribute("maxList", maxList);
	}
}
