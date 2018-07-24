package com.dowon.myboard.BoardController;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dowon.myboard.command.BoardCmd;
import com.dowon.myboard.command.BoardContentCmd;
import com.dowon.myboard.command.BoardDeleteCmd;
import com.dowon.myboard.command.BoardListCmd;
import com.dowon.myboard.command.BoardUpdateActionCmd;
import com.dowon.myboard.command.BoardUpdateCmd;
import com.dowon.myboard.command.BoardWriteCmd;
import com.dowon.myboard.command.JoinActionCmd;
import com.dowon.myboard.command.LoginActionCmd;
import com.dowon.myboard.command.UserDeleteCmd;
import com.dowon.myboard.command.UserUpdateActionCmd;
import com.dowon.myboard.command.UserUpdateCmd;

/**
 * 게시판 url 정보를 통해 Mapping을 하여 각 view에 뿌려주는 역할을 한다.
 */
@Controller
public class BoardController {

	BoardCmd boardCmd;

	// @ResponseBody
	// @RequestMapping("/ajax")
	// public String ajax(HttpServletRequest request, ModelMap modelMap) throws
	// Exception {
	// modelMap.addAttribute("request", request);
	//
	// boardCmd = new TestAjaxCmd();
	// boardCmd.execute(modelMap);
	//
	// System.out.println("test1 : " + modelMap.get("test1") + ", test2 : " +
	// modelMap.get("test2") + "\n\n\n");
	//
	// return "test";
	// }
	@RequestMapping("/list") // 게시판 전체 글 목록을 보여주는 메인화면.
	public String list(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
	
		boardCmd = new BoardListCmd();
		boardCmd.execute(model);

		return "list";
	}

	@RequestMapping("/content") // 게시글 내용을 보여주는 content 화면.
	public String content(HttpServletRequest request, Model model) {
		encodeUTF_8(request);

		model.addAttribute("request", request);

		boardCmd = new BoardContentCmd();
		boardCmd.execute(model);

		return "content";
	}

	@RequestMapping("/write_view") // 글작성화면을 보여주는 화면.
	public String wirte_view(HttpServletRequest request, Model model) {
		/*
		 * 로그인하지 않을 시 해당 화면은 들어갈 수 없다.
		 */
		if (!isLogin(request)) {
			return alert(model, "권한이 없습니다.", "list");
		} else {
			return "write_view";
		}
	}

	@RequestMapping("/write") // 글 작성 화면에서 실제 글을 작성하여 DB로 뿌려준다.
	public String write(HttpServletRequest request, Model model) {
		// HttpServletRequest를 왜 받냐면 위에서 작성한 write_view를 폼에서 받아야 하기 때문!
		encodeUTF_8(request);
		if (!isLogin(request)) {
			return alert(model, "권한이 없습니다.", "list");
		}

		model.addAttribute("request", request);
		// request 속성을 정해서 "request"에 담아버림. 어차피 컨트롤러에서 작업을 안 하고 서비스와 DAO에서 작업하기 때문
		// 같이 model에 통째로 넣어버림.

		boardCmd = new BoardWriteCmd();
		boardCmd.execute(model);

		return "redirect:list";
	}

	@RequestMapping("/delete") // 글을 삭제한다.
	public String delete(HttpServletRequest request, Model model) {
		if (!isLogin(request)) {
			return alert(model, "권한이 없습니다.", "list");
		}

		model.addAttribute("request", request);

		boardCmd = new BoardDeleteCmd();
		boardCmd.execute(model);

		return alert(model, "삭제 되었습니다.", "list");

	}

	@RequestMapping(method = RequestMethod.POST, value = "/content_update") // update 화면을 보여준다.
	public String update(HttpServletRequest request, Model model) {
		encodeUTF_8(request);

		if (!isLogin(request)) {
			return alert(model, "권한이 없습니다.", "list");
		}

		model.addAttribute("request", request);

		boardCmd = new BoardUpdateCmd();
		boardCmd.execute(model);

		return "content_update";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateAction") // update 화면에서 정보를 받아 DB에 뿌려준다.
	public String updateAction(HttpServletRequest request, Model model) {
		encodeUTF_8(request);

		if (!isLogin(request)) {
			return alert(model, "권한이 없습니다.", "list");
		}
		model.addAttribute("request", request);

		boardCmd = new BoardUpdateActionCmd();
		boardCmd.execute(model);

		return alert(model, "수정 되었습니다.", "list");
	}

	@RequestMapping("/alertAndRedirect") // alert 창을 띄운다.
	public String alertAndRedirect(HttpServletRequest request, Model model) {

		return "alertAndRedirect";
	}

	/*
	 * 로그인 여부를 체크한다.
	 */
	public boolean isLogin(HttpServletRequest request) {
		if (request.getSession().getAttribute("User") == null) {
			return false;
		} else {
			return true;
		}
	}

	/*
	 * 기본 인코딩을 UTF-8로 유지한다.
	 */
	public void encodeUTF_8(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	// 메세지와 url을 받아서 경고창을 띄운다.
	public String alert(Model model, String msg, String url) {

		model.addAttribute("message", msg);
		model.addAttribute("returnUrl", url);

		return "alertAndRedirect";
	}
}
