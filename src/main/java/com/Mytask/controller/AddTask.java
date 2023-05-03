package com.Mytask.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.Mytask.dao.TodoDao;
import com.Mytask.model.TodoModel;

/**
 * Servlet implementation class AddTask
 */
public class AddTask extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTask() {
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
		int cid=Integer.parseInt(request.getParameter("collectionId"));
		HttpSession session=request.getSession();
		int uid= (int)session.getAttribute("USER");
		String rawtitle=request.getParameter("title");
		String title=rawtitle.toUpperCase().charAt(0)+rawtitle.substring(1);
		String content=request.getParameter("content");
		String toggle=request.getParameter("toggle");
		String collectionName=request.getParameter("cName");
		boolean important=false;
		if(toggle!=null)
		{
			important=true;
		}
		TodoModel todoModel=new TodoModel(cid, uid, title, content, important);
		TodoDao todoDao=new TodoDao();
		int result=todoDao.AddTodo(todoModel);
		if(result>0)
		{
			response.sendRedirect("todo.jsp?id="+cid+"&name="+collectionName);
		}	
	}

}
