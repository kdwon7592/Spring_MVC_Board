package com.dowon.myboard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.dowon.myboard.dao.UserDAO;

public class LoginActionCmd implements BoardCmd<Model>{
	
	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String userId = request.getParameter("userId");
		String userPassword = request.getParameter("userPassword");
		
		System.out.println(userId + " " + userPassword);
		
		UserDAO userDao = new UserDAO();
		int result = userDao.login(userId, userPassword);
		//System.out.println(user.getUserId() + user.getUserPassword());
		if(result == 1){
			System.out.println("로그인 성공");
			request.getSession(true).setAttribute("User", userId);;
//			session.setAttribute("userId", user.getUserId());
//			PrintWriter script = response.getWriter();
//			script.println("<script>");
//			script.println("location.href = 'main.jsp'");
//			script.println("</script>");
		} else if(result == 0){
			System.out.println("아이디 또는 비밀번호가 틀립니다.");
//			PrintWriter script = response.getWriter();
//			script.println("<script>");
//			script.println("alert('비밀번호가 틀립니다.')");
//			script.println("history.back()");
//			script.println("</script>");
		} else if(result == -1){
			System.out.println("아이디 또는 비밀번호가 틀립니다.");
//			PrintWriter script = response.getWriter();
//			script.println("<script>");
//			script.println("alert('존재하지 않는 아이디입니다.')");
//			script.println("history.back()");
//			script.println("</script>");
		}
		
	}
}
