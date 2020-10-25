package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.City;
import vo.Province;
import dbc.DatabaseConnection;

public class ProvinceCityDao {
	public ArrayList<Province> getProvince(){
		ArrayList<Province> list=new ArrayList<Province>();
		DatabaseConnection dbc=new DatabaseConnection();
		Connection con=dbc.getConnection();
		try{
			String sql="select* from t_province";
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Province province=new Province(rs.getString("provinceCode"),rs.getString("provinceName"));
				list.add(province);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			dbc.close();
		}
		return list;
	}
	
	public ArrayList<City> getCity(String provinceCode){
		ArrayList<City> list=new ArrayList<City>();
		DatabaseConnection dbc=new DatabaseConnection();
		Connection con=dbc.getConnection();
		try{
			String sql="select* from t_city where provinceCode=?";
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, provinceCode);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				City city=new City(rs.getString("cityCode"),rs.getString("cityName"),rs.getString("provinceCode"));
				list.add(city);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			dbc.close();
		}
		return list;
	}
}
