package com.Mytask.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.Mytask.dao.TodoDao;

/**
 * Servlet implementation class UpdateTaskMain
 */
public class UpdateTaskMain extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateTaskMain() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		String cname=request.getParameter("cName");
		int cid=Integer.parseInt(request.getParameter("cId")) ;
		int tid=Integer.parseInt(request.getParameter("tId")) ;
		TodoDao todoDao=new TodoDao();
		int result=todoDao.UpdateTodoByTid(tid, title, content);
		if(result>0)
		{
			response.sendRedirect("todo.jsp?id="+cid+"&name="+cname);
		}
		
	}

}
