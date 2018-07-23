package com.dowon.myboard.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.dowon.myboard.dto.UserDTO;

public class LoginCheckInterceptor extends HandlerInterceptorAdapter{
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
		
		HttpSession session = request.getSession(false);
		
		System.out.println("인터셉터 작동?");
		
		/*if(session == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return false;
		}
		
		String userId = (String) session.getAttribute("User");
		
		if(userId == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			System.out.println("UserID가 null?");

			return false;
		}*/
		
		System.out.println("userID가 true??");
		
		return true;
	}
}
