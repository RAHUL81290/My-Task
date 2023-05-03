package com.Mytask.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.Mytask.dao.CollectionDao;
import com.Mytask.model.CollectionModel;

/**
 * Servlet implementation class AddCollection
 */
public class AddCollection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCollection() {
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
		String rawtitle=request.getParameter("collectionTitle");
		String title=rawtitle.toUpperCase().charAt(0)+rawtitle.substring(1);
		HttpSession session=request.getSession();
		int userId=(int) session.getAttribute("USER");
		CollectionModel collection=new CollectionModel(userId, title);
		CollectionDao collectioDoa=new CollectionDao();
		int result=collectioDoa.CreateCollection(collection);
		response.sendRedirect("todo.jsp?id="+result+"&name="+title);
	}

}
