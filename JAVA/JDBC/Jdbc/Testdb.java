package com.test;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Testdb {
	
	private static Connection conn; 
	
	public static void main(String[] args) {
		String id;
	 	String pw;
		String url;
		Connection conn;   // 연결을 위한 필요 변수들 생성 (현재 상태는 지역변수)
		
			id = "root";
			pw = "1234";
			url = "jdbc:mysql://localhost:3306/testdb";  
			// 변수들에 값을 할당 >> DriverManager.getConnection(url, id, pw) 에 전달된 인자값을 가짐.
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver"); // 드라이버 클래스 로드
				// forName() 메서드는 "com.mysql.cj.jdbc.Driver" 를 로드함.
				System.out.println("드라이버 연결 성공!"); 
				conn = DriverManager.getConnection(url, id, pw); // JDBC 주소, 아이디, 비번를 받음
				System.out.println("데이터 베이스 접속 성공!"); 
			} catch(ClassNotFoundException e) {
				e.printStackTrace();	// ClassNotFoundException 이 발생할 경우 해당 예외를 처리하고 출력
						// 이유는 프로그램이 멈추면 안되기때문.
			} catch(SQLException e) {
				e.printStackTrace(); // SQLException 이 발생한 경우 해당 예외를 처리하고 출력
			}		
		}
		
	    public static Connection getConn() {
	        return conn;
	    }  

}
