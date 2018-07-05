package com.dowon.myboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.dowon.myboard.dto.ReplyDTO;

public class ReplyDAO {
	DataSource dataSource;

	JdbcTemplate template;

	public ReplyDAO() {
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

	public ReplyDTO replyGet(int replyId) {
		ReplyDTO dto = new ReplyDTO();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		try {
			connection = dataSource.getConnection();
			String query = "select * from b_reply where rId = ?";
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, replyId);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				// while문으로 resultSet.next를 하지 않으면 sql exception 발생
				int rId = resultSet.getInt("rId");
				int bId = resultSet.getInt("bId");
				String rName = resultSet.getString("rName");
				String rComment = resultSet.getString("rComment");
				Timestamp rDate = resultSet.getTimestamp("rDate");

				dto = new ReplyDTO(rId, bId, rName, rComment, rDate);
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
		return dto;
	}
	
	public ArrayList<ReplyDTO> replyView(int boardId) {
		ArrayList<ReplyDTO> dtos = new ArrayList<ReplyDTO>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		try {
			connection = dataSource.getConnection();
			String query = "select * from b_reply where bId = ?";
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, boardId);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				// while문으로 resultSet.next를 하지 않으면 sql exception 발생
				int rId = resultSet.getInt("rId");
				int bId = resultSet.getInt("bId");
				String rName = resultSet.getString("rName");
				String rComment = resultSet.getString("rComment");
				Timestamp rDate = resultSet.getTimestamp("rDate");

				ReplyDTO dto = new ReplyDTO(rId, bId, rName, rComment, rDate);
				dtos.add(dto);
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
		return dtos;
	}

	public void replyWrite(String boardId, String rComment, String rName) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		try {
			connection = dataSource.getConnection();
			String query = "insert into b_reply(bId, rComment, rName) values(?, ?, ?)";
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(boardId));
			pstmt.setString(2, rComment);
			pstmt.setString(3, rName);
			
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
	
	public void replyUpdate(int rId, String rComment) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		try {
			connection = dataSource.getConnection();
			String query = "update b_reply set rComment = ? where rId = ?";
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, rComment);
			pstmt.setInt(2, rId);
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
	public void delete(int rId) {
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = dataSource.getConnection();
			String query = "delete from b_reply where rId = ?";
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, rId);

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
