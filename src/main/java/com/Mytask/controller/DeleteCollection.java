package com.Mytask.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.Mytask.dao.CollectionDao;
import com.Mytask.dao.TodoDao;

/**
 * Servlet implementation class DeleteCollection
 */
public class DeleteCollection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCollection() {
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
		int cid=Integer.parseInt(request.getParameter("cId"));
		
		TodoDao todoDao=new TodoDao();
		int todoDeleteResult= todoDao.deleteTodoByCid(cid);
		
		CollectionDao collDao=new CollectionDao(); 
		int collDeleteResult=collDao.deleteCollectingById(cid);
		
		if(collDeleteResult>0)
		{
			response.sendRedirect("dashboard.jsp");
		}
	}

}
