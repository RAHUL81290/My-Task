package com.Mytask.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Mytask.util.Util;

public class LoginDao {
	
	public int checkExist(String email,String password) {
		Connection con=Util.con();
		int result=0;
		boolean check=false;
		try {
			PreparedStatement ps=con.prepareStatement("SELECT * FROM user where email=? AND password=?");
			ps.setString(1,email);
			ps.setString(2,password);
			ResultSet rs=ps.executeQuery();
			check=rs.next();
			if(check)
			{
				result=rs.getInt(1); 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(con!=null)
			{
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return result; 
	}

}
