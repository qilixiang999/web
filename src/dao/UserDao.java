package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dbc.DatabaseConnection;
import vo.User;

public class UserDao {
	public User get(String userName) {
		User user = null;
		DatabaseConnection dbc=new DatabaseConnection();
		Connection con=dbc.getConnection();
		try{
			String sql="select* from t_user where userName=?";
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1,userName);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				user=new User(rs.getString("userName"),rs.getString("password"),rs.getString("chrName"),rs.getString("role"));
			}
			}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			dbc.close();
		}
		return user;
	}
	public String Check(String userName){
		String URL="";
		DatabaseConnection dbc=new DatabaseConnection();
		Connection con=dbc.getConnection();
		try{
			
		String sql="select* from t_resource where resourceId in("
				+ "select resourceId from t_role_resource where roleId in("
				+ "select roleId from t_user_role where userName=?)"
				+ ")";



		PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
		ps.setString(1,userName);
		ResultSet rs=ps.executeQuery();
		if(rs.next()){
			URL=rs.getString("url");
		}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			dbc.close();
		}
		return URL;
	}
	public static void main(String[] args) {
		UserDao u=new UserDao();
		u.get("admin");
	}
}
