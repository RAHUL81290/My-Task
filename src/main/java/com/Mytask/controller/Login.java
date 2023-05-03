package com.Mytask.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.Mytask.dao.LoginDao;
import com.mysql.cj.Session;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("login.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String rawemail=request.getParameter("email");
		String uemail=rawemail.toLowerCase();
		String upassword=request.getParameter("password");
		
		LoginDao loginDao=new LoginDao();
		int userId=loginDao.checkExist(uemail, upassword);
		if(userId>0)
		{
			HttpSession session=request.getSession();
			session.setAttribute("USER",userId);
			response.sendRedirect("dashboard.jsp");
		}
		else {
			RequestDispatcher dispatcher= request.getRequestDispatcher("login.jsp");
			request.setAttribute("NOTIFICATION", "wrong credentials");
			dispatcher.forward(request, response);
		}
	}

}
