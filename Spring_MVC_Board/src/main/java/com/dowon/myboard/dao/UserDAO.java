package com.dowon.myboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.dowon.myboard.dto.ReplyDTO;
import com.dowon.myboard.dto.UserDTO;

public class UserDAO {
	DataSource dataSource;

	JdbcTemplate template;

	public UserDAO() {
		try {
			/*
			 * tomcat의 database pool 정보에 접근한다.
			 */
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/mysql");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public int join(UserDTO user) {
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		String SQL = "INSERT INTO USER VALUES (?, ?, ?, ?, ?)";
		
		try {
			connection = dataSource.getConnection();
			pstmt = connection.prepareStatement(SQL);
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getUserPassword());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getUserGender());
			pstmt.setString(5, user.getUserEmail());
			
			return pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;  //디비 오류

	}

	public int login(String userId, String userPassword) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		try {
			connection = dataSource.getConnection();
			String query = "select * from USER where userId = ?";
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, userId);
			resultSet = pstmt.executeQuery();
			
			String dbPassword = null;
			while (resultSet.next()) {
				// while문으로 resultSet.next를 하지 않으면 sql exception 발생
				dbPassword = resultSet.getString("userPassword");
			}
			if(dbPassword.equals(userPassword)) {
				return 1;
			} 
		} catch (Exception e) {
				return -1;
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (pstmt != null)
					pstmt.close();
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
}
