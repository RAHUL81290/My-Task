package com.Mytask.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Mytask.model.UserModel;
import com.Mytask.util.Util;

public class RegisterDoa {
	
	public int registerUser(UserModel u) {
		Connection con=Util.con();
		int result=0;
		try {
			PreparedStatement ps=con.prepareStatement("INSERT INTO user(name,email,password) values(?,?,?)");
			ps.setString(1, u.getName());
			ps.setString(2, u.getEmail());
			ps.setString(3, u.getPassword());
			result = ps.executeUpdate();
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
	public boolean checkExisting(String email)
	{
		Connection con=Util.con();
		boolean result=false;
		try {
			PreparedStatement ps=con.prepareStatement("SELECT * FROM user WHERE email=?");
 		    ps.setString(1, email);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				result=true;
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
