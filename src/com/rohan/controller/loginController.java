package com.rohan.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rohan.dao.LoginDAO;
import com.rohan.dao.LoginDAOImpl;
import com.rohan.entity.Login;

public class loginController extends HttpServlet {

	LoginDAO loginDAO = null;

	public loginController() {
		loginDAO = new LoginDAOImpl();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// once user logged in we are storing tht user email in seesion
		HttpSession session = request.getSession();

		Login login = new Login();

		// getting email and password from the form and setting to setter of login
		login.setEmail(request.getParameter("email"));
		login.setPassword(request.getParameter("password"));

		String status = loginDAO.authenticate(login);

		if (status.equals("true")) {
			session.setAttribute("email", login.getEmail());
			response.sendRedirect("EmployeeServlet?action=LIST");
		}

		if (status.equals("false")) {
			response.sendRedirect("index.jsp?status=false");
		}

		if (status.equals("error")) {
			response.sendRedirect("index.jsp?status=error");
		}

	}

}
