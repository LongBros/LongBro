package com.longbro.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * 数据库连接工具
 * @author 赵成龙
 * @website www.longqcloud.cn & www.zy52113.com
 * @description
 * @version
 */
public class JdbcUtil {
	static Connection con=null;
	static Statement st=null;
	public static Statement getCon(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/music?useUnicode=true&characterEncoding=utf-8", "root", "123456");
			st=con.createStatement();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return st;
	}
}
