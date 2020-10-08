package dbc;

import java.sql.DriverManager;

import com.mysql.jdbc.Connection;

public class DatabaseConnection {
	// 准备数据库的四大参数
		private static String DBDRIVER = "com.mysql.jdbc.Driver";
		private static String DBURL = "jdbc:mysql://localhost:3306/exercise?useSSL=false";
		private static String DBUSER = "root";
		private static String PASSWORD = "enen7657";
		// 准备一个数据库连接对象
		private Connection con;
		
		static{
			try{
				Class.forName(DBDRIVER);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 构造方法，实例化对象时创建连接对象
		public DatabaseConnection() {
			try {
				this.con = (Connection) DriverManager.getConnection(DBURL, DBUSER, PASSWORD);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 直接返回实例化对象时创建的连接对象
		public Connection getConnection() {
			return this.con;
		}
		
		// 关闭连接对象
		public void close() {
			if (this.con != null) {
				try {
					this.con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
}
