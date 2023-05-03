package com.Mytask.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Mytask.model.TodoModel;
import com.Mytask.util.Util;
public class TodoDao {
	public List<TodoModel>getAllTodoByUid(int userid)
	{
		List<TodoModel>myTodos=new ArrayList<>();
		Connection con=Util.con();
		try {
			PreparedStatement ps=con.prepareStatement("SELECT * FROM task WHERE uid=?");
			ps.setInt(1, userid);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				int tid=rs.getInt(1);
				int cid=rs.getInt(2);
				int uid=rs.getInt(3);
				String title=rs.getString(4);
				String content=rs.getString(5);
				boolean important=rs.getBoolean(6);
				boolean completed=rs.getBoolean(7);
				myTodos.add(new TodoModel(tid, cid, uid, title, content,important, completed));
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
		
		return myTodos;
	}
	public List<TodoModel>getTodoById(int collectionid)
	{
		List<TodoModel>myTodos=new ArrayList<>();
		Connection con=Util.con();
		try {
			PreparedStatement ps=con.prepareStatement("SELECT * FROM task WHERE cid=?");
			ps.setInt(1, collectionid);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				int tid=rs.getInt(1);
				int cid=rs.getInt(2);
				int uid=rs.getInt(3);
				String title=rs.getString(4);
				String content=rs.getString(5);
				boolean important=rs.getBoolean(6);
				boolean completed=rs.getBoolean(7);
				myTodos.add(new TodoModel(tid, cid, uid, title, content,important, completed));
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
		
		return myTodos;
	}
	public int AddTodo(TodoModel todoModel) 
	{
		Connection con= Util.con();
		int result=0;
		try {
			PreparedStatement ps=con.prepareStatement("INSERT INTO task(cid,uid,title,content,important) VALUES(?,?,?,?,?)");
			ps.setInt(1,todoModel.getCid());
			ps.setInt(2,todoModel.getUid());
			ps.setString(3, todoModel.getTitle());
			ps.setString(4, todoModel.getContent());
			ps.setBoolean(5, todoModel.isImportant());
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
	public int deleteTodoByCid(int cId) {
		int result=0;
		Connection con=Util.con();
		try {
			PreparedStatement ps=con.prepareStatement("DELETE FROM task WHERE cid=?");
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
	public int deleteTodoByTid(int tId) {
		int result=0;
		Connection con=Util.con();
		try {
			PreparedStatement ps=con.prepareStatement("DELETE FROM task WHERE tid=?");
			ps.setInt(1, tId);
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
	public int CompleteTodoByTid(int tId) {
		int result=0;
		Connection con=Util.con();
		int set=1;
		try {
			PreparedStatement ps=con.prepareStatement("UPDATE task SET completed='"+set+"' WHERE tid=?");
			ps.setInt(1, tId);
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
	public int UpdateTodoByTid(int tId,String title,String content) {
		int result=0;
		Connection con=Util.con();
		try {
			PreparedStatement ps=con.prepareStatement("UPDATE task SET title=?,content=? WHERE tid=?");
			ps.setString(1, title);
			ps.setString(2, content);
			ps.setInt(3, tId);
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
