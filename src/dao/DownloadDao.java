package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.Download;

public class DownloadDao {
	public ArrayList<Download> down() {
		ArrayList<Download> list = new ArrayList<Download>();
		try {
			// 1.加载Mysql驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 2.建立与MySQL服务器的连接
			Connection con = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/exercise?useSSL=false",
					"root", "enen7657");
			String sql = "select* from t_downloadList where id";
			PreparedStatement ps = (PreparedStatement) con
					.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Download dd = new Download(rs.getInt("id"),
						rs.getString("name"), rs.getString("path"),
						rs.getString("description"), rs.getString("size"),
						rs.getInt("star"), rs.getString("image"));
				list.add(dd);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public Download get(int id) {
		Download download = null;
		try {
			// 1.加载Mysql驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 2.建立与MySQL服务器的连接
			Connection con = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/exercise?useSSL=false",
					"root", "enen7657");
			String sql = "select* from t_downloadList where id=?";
			PreparedStatement ps = (PreparedStatement) con
					.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				download = new Download(rs.getInt("id"),
						rs.getString("name"), rs.getString("path"),
						rs.getString("description"), rs.getString("size"),
						rs.getInt("star"), rs.getString("image"));
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return download;
	}

	public static void main(String[] args) {
		DownloadDao dld = new DownloadDao();
		ArrayList<Download> list = dld.down();
		for (int i = 0; i < list.size(); i++) {

			System.out.println(list.get(i).toString());
		}
	}
}
