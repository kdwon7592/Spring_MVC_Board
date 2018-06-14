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

import com.dowon.myboard.dto.BoardDTO;

public class BoardDAO {

	DataSource dataSource;

	JdbcTemplate template;

	public BoardDAO() {
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

	public ArrayList<BoardDTO> list() {

		ArrayList<BoardDTO> dtos = new ArrayList<BoardDTO>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		try {
			connection = dataSource.getConnection();
			String query = "select * from b_board order by bId";
			/*
			 * s_board에 b_board에 접근하여 정보를 가져온다.
			 */
			pstmt = connection.prepareStatement(query);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				//bContent는 게시판 목록 불러오기에 필요 없다. 추후 수정 필요.
				int bHit = resultSet.getInt("bHit");
				Timestamp bDate = resultSet.getTimestamp("bDate");

				BoardDTO dto = new BoardDTO(bId, bName, bTitle, bContent, bHit, bDate);
				dtos.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(resultSet != null) resultSet.close();
				if(pstmt != null) pstmt.close();
				if(connection != null) connection.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return dtos;
	}
	
}
