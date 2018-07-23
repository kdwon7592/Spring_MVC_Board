package com.dowon.myboard.BoardController;

import java.io.UnsupportedEncodingException;

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
import com.dowon.myboard.command.ReplyDeleteCmd;
import com.dowon.myboard.command.ReplyUpdateActionCmd;
import com.dowon.myboard.command.ReplyUpdateCmd;
import com.dowon.myboard.command.BoardUpdateActionCmd;
import com.dowon.myboard.command.BoardUpdateCmd;
import com.dowon.myboard.command.BoardWriteCmd;
import com.dowon.myboard.command.JoinActionCmd;
import com.dowon.myboard.command.LoginActionCmd;
import com.dowon.myboard.command.ReplyWriteCmd;
import com.dowon.myboard.command.TestAjaxCmd;
import com.dowon.myboard.command.UserDeleteCmd;
import com.dowon.myboard.command.UserUpdateActionCmd;
import com.dowon.myboard.command.UserUpdateCmd;

/**
 * 게시판 url 정보를 통해 Mapping을 하여 각 view에 뿌려주는 역할을 한다.
 */

@Controller
public class ReplyController {

	BoardCmd boardCmd;


	@RequestMapping(value = "/reply_write", method = RequestMethod.POST) //content화면에서 댓글을 쓴다.
	public String reply_write(HttpServletRequest request, Model model) {
		encodeUTF_8(request);

		if (!isLogin(request)) {
			return alert(model, "권한이 없습니다.", "list");
		}
		model.addAttribute("request", request);
		String bId = request.getParameter("bId"); //댓글을 작성 후 댓글 작성하던 게시글로 다시 돌아가기 위함.

		boardCmd = new ReplyWriteCmd();
		boardCmd.execute(model);

		return "redirect:content?bId=" + bId;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/reply_update") //댓글을 수정하는 화면
	public String reply_update(HttpServletRequest request, Model model) {
		encodeUTF_8(request);

		if (!isLogin(request)) {
			return alert(model, "권한이 없습니다.", "list");
		}

		model.addAttribute("request", request);

		boardCmd = new ReplyUpdateCmd();
		boardCmd.execute(model);

		return "reply_update";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/reply_updateAction") //수정된 댓글을 DB로 뿌려준다.
	public String reply_updateAction(HttpServletRequest request, Model model) {
		encodeUTF_8(request);

		if (!isLogin(request)) {
			return alert(model, "권한이 없습니다.", "list");
		}

		model.addAttribute("request", request);
		String bId = request.getParameter("bId");
		boardCmd = new ReplyUpdateActionCmd();
		boardCmd.execute(model);

		return alert(model, "수정 되었습니다.", "content?bId=" + bId);
		
	}


	@RequestMapping("/reply_delete") //댓글을 삭제한다.
	public String reply_delete(HttpServletRequest request, Model model) {
		if (!isLogin(request)) {
			return alert(model, "권한이 없습니다.", "list");
		}

		model.addAttribute("request", request);
		String bId = request.getParameter("bId");

		boardCmd = new ReplyDeleteCmd();
		boardCmd.execute(model);

		return alert(model, "삭제 되었습니다.", "content?bId=" + bId);
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
	
	//메세지와  url을 받아서 경고창을 띄운다.
	public String alert(Model model, String msg, String url) {

		model.addAttribute("message", msg);
		model.addAttribute("returnUrl", url);

		return "alertAndRedirect";
	}
}
