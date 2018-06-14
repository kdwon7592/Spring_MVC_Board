package com.dowon.myboard.BoardController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dowon.myboard.command.BoardCmd;
import com.dowon.myboard.command.BoardListCmd;

/**
 * 게시판 url 정보를 통해  Mapping을 하여 각 view에 뿌려주는 역할을 한다.
 */

@Controller
public class BoardController {
	
	BoardCmd boardCmd;
	
	@RequestMapping("/list") // 게시판 정보를 뿌려준다.
	public String list(Model model) {
		
		System.out.println("list");
		
		boardCmd = new BoardListCmd();
		boardCmd.execute(model);		
		
		return "list";
	}
	
	@RequestMapping("/dbtest")
	public String dbtest() {
		System.out.println("dbtest()");
				
		return "DBConnectTest";
	}
	
	@RequestMapping("/dbcptest")
	public String dbcptest() {
		System.out.println("dbcptest()");
		
		return "DBCPConnectTest";
	}
}
