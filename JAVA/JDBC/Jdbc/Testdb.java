package Jdbc;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Testdb {
	public static void main(String[] args) {
		String id;
	 	String pw;
		String url;
		Connection conn;
		
			id = "root";
			pw = "1234";
			url = "jdbc:mysql://localhost:3306/Shopping";
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				System.out.println("드라이버 연결 성공!");
				conn = DriverManager.getConnection(url, id, pw);
				System.out.println("데이터 베이스 접속 성공!");
			} catch(ClassNotFoundException e) {
				e.printStackTrace();
			} catch(SQLException e) {
				e.printStackTrace();
			}		
		}
		
		public Connection getConn() {
			return getConn();
		}

}
