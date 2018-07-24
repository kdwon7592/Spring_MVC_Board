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
public class UserController {

	BoardCmd boardCmd;

	@RequestMapping("/join") // 회원 가입 화면을 보여준다.
	public String join(HttpServletRequest request, Model model) {
		if (isLogin(request)) {
			return alert(model, "Already Login", "list");
		}
		return "join";
	}

	@RequestMapping("/joinAction") // 회원 가입 정보를 DB에 뿌려준다.
	public String joinAction(HttpServletRequest request, Model model) {
		encodeUTF_8(request);

		if (isLogin(request)) {
			return alert(model, "Already Login", "list");
		}

		model.addAttribute("request", request);
		boardCmd = new JoinActionCmd();
		boardCmd.execute(model);

		if(request.getAttribute("result").equals(1)) {
			return alert(model, "회원가입이 되었습니다.", "list");
		}else if(request.getAttribute("result").equals(-2)){
			return alert(model, "입력되지 않은 정보가 있습니다.", "join");
		}else {
			return alert(model, "이미 가입되어있는 아이디입니다.", "join");
		}
	}

	@RequestMapping("/login") // 로그인 화면을 보여준다.
	public String login(HttpServletRequest request, Model model) {
		encodeUTF_8(request);

		if (isLogin(request)) {
			return alert(model, "Already Login", "list");
		}

		return "login";
	}

	@RequestMapping("/loginAction") // 로그인을 수행한다.
	public String loginAction(HttpServletRequest request, Model model) {
		encodeUTF_8(request);

		if (isLogin(request)) {
			return alert(model, "Already Login", "list");
		}

		model.addAttribute("request", request);
		
		boardCmd = new LoginActionCmd();
		boardCmd.execute(model);

		if(request.getAttribute("result").equals(1)) {
			return alert(model, "Login OK!!", "list");
		}else {
			return alert(model, "정보가 틀립니다.", "login");
		}
	}

	@RequestMapping("/logout") // 로그아웃을 수행한다.
	public String logout(HttpServletRequest request, Model model) {

		request.getSession().invalidate();

		return alert(model, "LogOut!!", "list");
	}

	@RequestMapping("/user_update") // 회원 정보를 수정한다.
	public String user_update(HttpServletRequest request, Model model) {
		encodeUTF_8(request);

		if (!isLogin(request)) {
			return alert(model, "권한이 없습니다.", "list");
		}

		model.addAttribute("request", request);

		boardCmd = new UserUpdateCmd();
		boardCmd.execute(model);

		return "user_update";

	}

	@RequestMapping(method = RequestMethod.POST, value = "/user_updateAction")
	public String user_updateAction(HttpServletRequest request, Model model) { // 수정된 회원 정보를 DB에 뿌려준다.
		encodeUTF_8(request);

		if (!isLogin(request)) {
			return alert(model, "권한이 없습니다.", "list");
		}
		
		model.addAttribute("request", request);

		boardCmd = new UserUpdateActionCmd();
		boardCmd.execute(model);

		return "redirect:list";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/user_delete")
	public String user_delete(HttpServletRequest request, Model model) { // 회원 탈퇴를 한다.
		encodeUTF_8(request);

		if (!isLogin(request)) {
			return alert(model, "권한이 없습니다.", "list");
		}

		model.addAttribute("request", request);

		boardCmd = new UserDeleteCmd();
		boardCmd.execute(model);

		return "redirect:logout";
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
