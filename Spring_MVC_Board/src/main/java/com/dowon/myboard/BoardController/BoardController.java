package com.dowon.myboard.BoardController;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dowon.myboard.command.BoardCmd;
import com.dowon.myboard.command.BoardContentCmd;
import com.dowon.myboard.command.BoardDeleteCmd;
import com.dowon.myboard.command.BoardListCmd;
import com.dowon.myboard.command.BoardUpdateActionCmd;
import com.dowon.myboard.command.BoardUpdateCmd;
import com.dowon.myboard.command.BoardWriteCmd;
import com.dowon.myboard.command.ReplyWriteCmd;
import com.dowon.myboard.command.TestAjaxCmd;

/**
 * 게시판 url 정보를 통해 Mapping을 하여 각 view에 뿌려주는 역할을 한다.
 */

@Controller
public class BoardController {

	BoardCmd boardCmd;

	@RequestMapping("/test")
	public String test(Model model) {
		return "test";
	}

	@RequestMapping("/test2")
	public String test2(Model model) {
		return "test2";
	}

	@RequestMapping(value = "/reply_write")
	@ResponseBody
	public String reply_write(HttpServletRequest request, ModelMap modelMap) throws Exception {
		modelMap.addAttribute("request", request);
		String rName = request.getParameter("rName");
		String rComment = request.getParameter("rComment");
		String bId = request.getParameter("bId");

		System.out.println("Controller : " + rName + " " + rComment + " " + bId);

		boardCmd = new ReplyWriteCmd();
		boardCmd.execute(modelMap);
		
		return "content";
	}

	@ResponseBody
	@RequestMapping("/ajax")
	public String ajax(HttpServletRequest request, ModelMap modelMap) throws Exception {
		modelMap.addAttribute("request", request);

		boardCmd = new TestAjaxCmd();
		boardCmd.execute(modelMap);

		System.out.println("test1 : " + modelMap.get("test1") + ", test2 : " + modelMap.get("test2") + "\n\n\n");

		return "test";
	}

	@RequestMapping("/list") // 게시판 정보를 뿌려준다.
	public String list(Model model) {

		System.out.println("list");

		boardCmd = new BoardListCmd();
		boardCmd.execute(model);

		return "list";
	}

	@RequestMapping("/content")
	public String content(HttpServletRequest request, Model model) {
		System.out.println("content");
		model.addAttribute("request", request);

		boardCmd = new BoardContentCmd();
		boardCmd.execute(model);

		return "content";
	}

	@RequestMapping("/write_view")
	public String wirte_view(Model model) {

		System.out.println("write_view");

		return "write_view";
	}

	@RequestMapping("/write")
	public String write(HttpServletRequest request, Model model) {
		// HttpServletRequest를 왜 받냐면 위에서 작성한 write_view를 폼에서 받아야 하기 때문!

		System.out.println("write()");

		model.addAttribute("request", request);
		// request 속성을 정해서 "request"에 담아버림. 어차 컨트롤러에서 작업을 안 하고 서비스와 DAO에서 작업하기 때문
		// 같이 model에 통째로 넣어버림.

		boardCmd = new BoardWriteCmd();
		boardCmd.execute(model);

		return "redirect:list";
	}

	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		boardCmd = new BoardDeleteCmd();
		boardCmd.execute(model);

		return "redirect:list";

	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateAction")
	public String updateAction(HttpServletRequest request, Model model) {

		model.addAttribute("request", request);

		boardCmd = new BoardUpdateActionCmd();
		boardCmd.execute(model);

		return "redirect:list";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/update")
	public String update(HttpServletRequest request, Model model) {

		model.addAttribute("request", request);

		boardCmd = new BoardUpdateCmd();
		boardCmd.execute(model);

		return "update";
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
