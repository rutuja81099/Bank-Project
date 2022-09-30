package com.aurionpro.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.aurionpro.model.Admin;
import com.aurionpro.model.LoginDao;
import com.aurionpro.model.User;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginController() {
		super();
	}

	@Resource(name = "jdbc/banking")
	private DataSource dataSource;

	LoginDao loginDao;

	@Override
	public void init() throws ServletException {
		super.init();

		loginDao = new LoginDao(dataSource);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());

		String userType = request.getParameter("userType");

		if (userType == null) {
			userType = "login";
		}

		System.out.println(userType);
		
//			HttpSession session = request.getSession();
//		
//			Object tempuser = (Object) session.getAttribute("isUser");

			switch (userType) {
			case "admin":
				checkAdminCredentials(request, response);
				break;
			case "user":
				checkUserCredentials(request, response);
				break;
			case "login":
				RequestDispatcher dispature = request.getRequestDispatcher("login.jsp");
				dispature.forward(request, response);
			}

			
		}

	private void checkUserCredentials(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		User user = new User(email, password);

		User isUser = loginDao.checkUserCredentials(user);
		System.out.println(isUser);

		if (isUser != null) {
			HttpSession session = request.getSession();
			session.setAttribute("isUser", isUser);

//			int acNo = isUser.getAcNo();
//			request.setAttribute("acNo", acNo);

			request.setAttribute("balance", isUser.getBalance());
			RequestDispatcher dispatcher = request.getRequestDispatcher("user-dashboard.jsp");
			dispatcher.forward(request, response);
		} else {
			String msg = "Invalid credentials...";
			request.setAttribute("msg", msg);
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}

	}

	private void checkAdminCredentials(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		Admin admin = new Admin(email, password);

		Admin isAdmin = loginDao.checkAdminCredentials(admin);

		if (isAdmin != null) {

			HttpSession session = request.getSession();
			session.setAttribute("isAdmin", isAdmin);

			RequestDispatcher dispatcher = request.getRequestDispatcher("admin-dashboard.jsp");
			dispatcher.forward(request, response);
		} else {
			String msg = "Invalid credentials...";
			request.setAttribute("msg", msg);
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
