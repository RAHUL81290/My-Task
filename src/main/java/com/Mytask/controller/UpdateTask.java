package com.Mytask.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.Mytask.dao.TodoDao;

/**
 * Servlet implementation class UpdateTask
 */
public class UpdateTask extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateTask() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("dashboard.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int tId=Integer.parseInt(request.getParameter("tId"));
		int cId=Integer.parseInt(request.getParameter("cId"));
		String cName=request.getParameter("cName");
		String tTitle=request.getParameter("tTitle");
		String tContent=request.getParameter("tContent");
		RequestDispatcher rs=request.getRequestDispatcher("todo.jsp?id="+cId+"&name="+cName);
		request.setAttribute("UPDATE","YES");
		request.setAttribute("TASKTITLE",tTitle);
		request.setAttribute("TASKCONTENT",tContent);
		request.setAttribute("TASKID",tId);
		rs.forward(request, response);
	}

}
