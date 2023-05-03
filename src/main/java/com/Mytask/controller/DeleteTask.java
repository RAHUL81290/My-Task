package com.Mytask.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.Mytask.dao.TodoDao;

/**
 * Servlet implementation class DeleteTask
 */
public class DeleteTask extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteTask() {
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
		int tId=Integer.parseInt(request.getParameter("tId"));
		int cId=Integer.parseInt(request.getParameter("cId"));
		String cName=request.getParameter("cName");
		TodoDao todoDao=new TodoDao();
		int result=todoDao.deleteTodoByTid(tId);
		if(result>0)
		{
			response.sendRedirect("todo.jsp?id="+cId+"&name="+cName);
		}
	}

}
