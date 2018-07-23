package com.dowon.myboard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.dowon.myboard.dao.UserDAO;
import com.dowon.myboard.dto.UserDTO;

public class JoinActionCmd implements BoardCmd<Model>{
	
	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String userId = request.getParameter("userId");
		String userPassword = request.getParameter("userPassword");
		String userName = request.getParameter("userName");
		String userGender = request.getParameter("userGender");
		String userEmail = request.getParameter("userEmail");
		
		UserDTO user = new UserDTO(userId, userPassword, userName, userGender, userEmail);
		
//		if(session.getAttribute("userId") != null){
//			userId = (String) session.getAttribute("userId");
//		}
//		if(userId != null){
//			PrintWriter script = response.getWriter();
//			script.println("<script>");
//			script.println("alert('이미 로그인이 되있습니다.')");
//			script.println("location.href = 'main.jsp'");
//			script.println("</script>");
//		}
		
		
		if (userId.replaceAll(" ", "") == "" || userPassword.replaceAll(" ", "") == "" || userName.replaceAll(" ", "") == "") {
			//null 이 아니라 ""값으로 체크해야함. 이유는 뭐지...
			System.out.println("입력 안된값 있음.");
			
			/*
			 * 이부분 처리 제대로 안됨 확인 필요.
			 */
		} else {
			UserDAO userDao = new UserDAO();
			int result = userDao.join(user);
			System.out.println(result);
			if (result == -1) {
				System.out.println("error");
			} else if (result >= 0) {
				System.out.println("success");
//				session.setAttribute("userId", user.getUserId());
			}
		}
		
	}
}
