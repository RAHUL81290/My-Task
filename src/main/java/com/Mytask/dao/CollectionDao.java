package com.Mytask.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Mytask.model.CollectionModel;
import com.Mytask.util.Util;

public class CollectionDao {
	public List<CollectionModel>getAllCollectionById(int userid){
		List<CollectionModel>mycollections=new ArrayList<>();
		Connection con=Util.con();
		try {
			PreparedStatement ps=con.prepareStatement("SELECT * FROM collection WHERE uid=?");
			ps.setInt(1,userid);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				int cid=rs.getInt(1);
				int uid=rs.getInt(2);
				String title=rs.getString(3);
				mycollections.add(new CollectionModel(cid, uid, title));
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
		return mycollections;
	}
	public int CreateCollection(CollectionModel collection) {
		Connection con=Util.con();
		int result=0;
		try {
			PreparedStatement ps=con.prepareStatement("INSERT INTO collection(uid,title) VALUES(?,?)");
			ps.setInt(1,collection.getUid());
			ps.setString(2,collection.getTitle());
			ps.executeUpdate();
			PreparedStatement ps1=con.prepareStatement("SELECT * FROM collection WHERE cid=last_insert_id()");
			ResultSet rs=ps1.executeQuery();
			if(rs.next())
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
	public int deleteCollectingById(int cId) {
		int result=0;
		Connection con=Util.con();
		try {
			PreparedStatement ps=con.prepareStatement("DELETE FROM collection WHERE cid=?");
			ps.setInt(1, cId);
			result=ps.executeUpdate();
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
