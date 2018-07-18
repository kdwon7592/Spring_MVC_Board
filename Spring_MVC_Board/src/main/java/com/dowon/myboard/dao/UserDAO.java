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
	
	public UserDTO getUser(String userId) {

		UserDTO user = new UserDTO();
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "select * from USER where userId = ?";
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, userId);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				// while문으로 resultSet.next를 하지 않으면 sql exception 발생
				String uId = resultSet.getString("userID");
				String uPassword = resultSet.getString("userPassword");
				String uName = resultSet.getString("userName");
				String uGender = resultSet.getString("userGender");
				String uEmail = resultSet.getString("userEmail");
				
				user = new UserDTO(uId, uPassword, uName, uGender, uEmail);
			}
		} catch (Exception e) {
			e.printStackTrace();
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
		return user;
	}
	
	public void update(String userId, String userPassword, String userName, String userEmail) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		try {
			connection = dataSource.getConnection();
			String query = "update USER set userPassword = ?, userName = ?, userEmail = ? where userId = ?";
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, userPassword);
			pstmt.setString(2, userName);
			pstmt.setString(3, userEmail);
			pstmt.setString(4, userId);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
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
	}
	public void delete(String userId) {
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = dataSource.getConnection();
			String query = "delete from USER where userId = ?";
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, userId);

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (connection != null)
					connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
