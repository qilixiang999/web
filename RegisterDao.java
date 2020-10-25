package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import dbc.DatabaseConnection;

public class RegisterDao {
	public boolean register(String userName,String password,String chrName){
		DatabaseConnection dbc=new DatabaseConnection();
		Connection con=dbc.getConnection();
		boolean t=false;
		try{
			String sql="insert into t_user values(?,?,?,?)";
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, userName);
			ps.setString(2, password);
			ps.setString(3, chrName);
			ps.setString(4, "∆’Õ®”√ªß");
			ps.executeUpdate();
			t=true;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			dbc.close();
		}
		return t;
	}
}
