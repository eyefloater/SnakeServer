package com.gaby;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SnakeLogin extends javax.servlet.http.HttpServlet implements
		javax.servlet.Servlet {

	private static final long serialVersionUID = 1L;
	private static final String USER = "user";
	private static final String PW = "pw";

	public SnakeLogin() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("Snake Server says: Please, log in");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String userName = request.getParameter(USER);
		String password = request.getParameter(PW);
		HttpSession session = request.getSession(true);
		session.setAttribute(USER, userName);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		if (SnakeUser.validateUser(userName, password))	{
			out.println("Snake Login says: Hi \"" + userName
					+ "\", \" ready to play?");
			Cookie cookie = new Cookie("user", userName);
			response.addCookie(cookie);
		} else {
			out.println("Snake Login credentials are not correct");
		}
	}

}