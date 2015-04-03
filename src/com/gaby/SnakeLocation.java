package com.gaby;

import java.awt.Point;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SnakeLocation extends javax.servlet.http.HttpServlet implements
		javax.servlet.Servlet {

	private static final long serialVersionUID = 1L;

	public SnakeLocation() {
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

		Cookie[] cookies = request.getCookies();

		String locxStr = request.getParameter("locx");
		String locyStr = request.getParameter("locy");
		String userStr = request.getParameter("user");
		String gameStr = request.getParameter("game");
		int x = 0;
		try {
			x = Integer.parseInt(locxStr);
		} catch (Exception e) {
		}
		int y = 0;
		try {
			y = Integer.parseInt(locyStr);
		} catch (Exception e) {
		}
		SnakeDb.setLocation(userStr, new Point(x, y));
		HttpSession session = request.getSession(true);
		response.setContentType("text/html");
		SnakeGame game = SnakeDb.addUser(userStr);
		String otherUser = SnakeDb.getOtherUser(userStr);
		if (otherUser != null) {
			Point loc = SnakeDb.getLocationOtherUser(otherUser);
			if (loc != null)	{
				response.setHeader("locx", "" + loc.x);
				response.setHeader("locy", "" + loc.y);
				response.setHeader("user", otherUser);
			}
		} else {
		}
		PrintWriter out = response.getWriter();
		out.println("Snake says: got your location");

	}

}