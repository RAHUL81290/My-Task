package com.Mytask.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Mytask.util.Util;

public class UserDao {
	public String getUserNameById(int userId) {
		String uname="";
		Connection con=Util.con();
		try {
			PreparedStatement ps=con.prepareStatement("SELECT * FROM user WHERE uid=?");
			ps.setInt(1, userId);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				uname=rs.getString(2);
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
		return uname;
	}
}
