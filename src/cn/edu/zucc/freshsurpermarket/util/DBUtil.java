package cn.edu.zucc.freshsurpermarket.util;

import java.sql.Connection;

public class DBUtil {
	private static final String jdbcUrl="jdbc:mysql://localhost:3306/freshonlinesupermarket?characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai";
	private static final String dbUser="root";
	private static final String dbPwd="root";
	static{
		try {
//			Class.forName("com.mysql.jdbc.Driver");
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Connection getConnection() throws java.sql.SQLException{
		return java.sql.DriverManager.getConnection(jdbcUrl, dbUser, dbPwd);
	}
}
