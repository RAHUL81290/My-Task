package com.Mytask.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Mytask.dao.RegisterDoa;
import com.Mytask.model.UserModel;
import com.mysql.cj.protocol.Resultset;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("register.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uname = request.getParameter("name");
		String rawemail = request.getParameter("email");
		String uemail=rawemail.toLowerCase();
		String upassword = request.getParameter("password");
		
		RegisterDoa register=new RegisterDoa();
		
		boolean exist=register.checkExisting(uemail);
		
		if(exist==false)
		{
			if(uname.length()>=3 && uemail.length()>=11 && upassword.length()>=3)
			{
				UserModel user=new UserModel();
				user.setName(uname);
				user.setEmail(uemail);
				user.setPassword(upassword);
				int result=register.registerUser(user);
				if(result==1) {
					request.setAttribute("NOTIFICATION","user successfully created!!");
				}
				else {
					request.setAttribute("NOTIFICATION","error occured");
				}
			}
			else {
				request.setAttribute("NOTIFICATION", "invalid credentials");
			}
		}
		else {
			request.setAttribute("NOTIFICATION", "user exist");
		}
		

		RequestDispatcher dispatcher=request.getRequestDispatcher("register.jsp");
		dispatcher.forward(request, response);

	}

}
