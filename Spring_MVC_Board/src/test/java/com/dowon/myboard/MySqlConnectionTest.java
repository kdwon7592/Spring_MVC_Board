package com.dowon.myboard;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;

public class MySqlConnectionTest {
	private static final String Driver = "com.mysql.jdbc.Driver";
	private static final String Url = "jdbc:mysql://192.168.3.26:3306/s_board";
	private static final String User = "board";
	private static final String Pw = "rlaehdnjs123";

	@Test
	public void test() throws SQLException {
		try {
			Class.forName(Driver);
			Connection con = DriverManager.getConnection(Url,User,Pw);
			System.out.println(con);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
