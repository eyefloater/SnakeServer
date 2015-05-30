package com.gaby;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import io.shamanic.snakedb.*;
import io.shamanic.snakedb.SnakeUser;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class AdminServlet extends javax.servlet.http.HttpServlet implements
		javax.servlet.Servlet {

	private SqlSessionFactory sessionFactory;

	private static final long serialVersionUID = 1L;
	private static boolean canLogin;

	private SnakeUser user;

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
		
		try {
			initSessionFactory();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		SqlSession session = sessionFactory.openSession();
		
		try {
			Mapper mapper = session.getMapper(Mapper.class);
			user = mapper.getSnakeUser(username);
			// System.out.println("result, new method: " + user.getSnakeuser());

		} finally {
			session.close();
		}
		int attempts = user.getFailed_login_attempts();

		try {
			canLogin = validateUser(username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (canLogin) {

			user.setFailed_login_attempts(0);
			//String numberOfPlayers = request.getParameter("numberOfPlayers");
			String numberOfPlayers = "1";
			request.setAttribute("numberOfPlayers",numberOfPlayers);
			request.getRequestDispatcher("/WEB-INF/AdminPanel.jsp").forward(request,
					response);
			//RequestDispatcher rd = getServletContext().getRequestDispatcher("/AdminServlet");
			//rd.forward(request, response);
			//response.sendRedirect(path + "/AdminPanel.jsp");

		} else {

			user.setFailed_login_attempts(attempts++);
			response.sendRedirect(path + "/error.jsp");
		}
	}

	private boolean validateUser(String username, String password)
			throws Exception {

		return (user.getPassword().equals(password));
	}

	public void initSessionFactory() throws Exception {
		Reader rdr = Resources.getResourceAsReader("snakedbConfig.xml");
		sessionFactory = new SqlSessionFactoryBuilder().build(rdr);
		rdr.close();
	}

	private void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String numberOfPlayers = null;
		int number = 0;
		try {
			number = Integer.valueOf(numberOfPlayers);
		} catch (Exception ex) {

		}
		String message = "AdminServlet message here";
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