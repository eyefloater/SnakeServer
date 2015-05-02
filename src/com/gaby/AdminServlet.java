package com.gaby;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminServlet extends javax.servlet.http.HttpServlet implements
		javax.servlet.Servlet {

	private static final long serialVersionUID = 1L;
	private static boolean canLogin;
	private static String db;

	public AdminServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		performTask(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String path = request.getContextPath();

		canLogin = com.gaby.SnakeUser.validateUser(username, password);

		if (canLogin) {

			response.sendRedirect(path + "/AdminPanel.jsp");

		} else {
			response.sendRedirect(path + "/error.jsp");
		}
	}

	private void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		String numberOfPlayers = request.getParameter("numPlayers");
		int number = 0;
		try {
			number = Integer.valueOf(numberOfPlayers);
		} catch (Exception ex) {

		}
		String message = "AdminServlet message here";
		db = SnakeDb.GetSnakeDbName();
		ArrayList<String> players = new ArrayList<String>();
		switch (number) {
		case 0:
			// TODO: later
		case 1:
			SnakeGame game = SnakeDb.GetOnePlayerGame(request
					.getParameter("username"));
			players.add(request.getParameter("username"));

		case 2:

			SnakeDb.getTwoPlayerGame(request.getParameter("username"));
			// players.add(e)
		}

		// request.setAttribute("SnakePlayer", players.get(0));
		request.setAttribute("numberOfPlayers", players.size());
		request.setAttribute("message", message);
		request.getRequestDispatcher("/WEB-INF/page.jsp").forward(request,
				response);
		// PrintWriter out = response.getWriter();
		// out.println("Snake AdminServlet says hi");
	}

}