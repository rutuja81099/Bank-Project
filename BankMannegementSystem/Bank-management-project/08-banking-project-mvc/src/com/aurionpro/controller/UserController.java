package com.aurionpro.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.aurionpro.model.Passbook;
import com.aurionpro.model.User;
import com.aurionpro.model.UserDao;

@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserController() {
		super();
	}

	@Resource(name = "jdbc/banking")
	private DataSource dataSource;

	UserDao userDao;

	@Override
	public void init() throws ServletException {
		super.init();

		userDao = new UserDao(dataSource);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String command = request.getParameter("command");

		System.out.println(command);

		HttpSession session = request.getSession();


		Object user = (Object) session.getAttribute("isUser");

		if (user != null) {
			switch (command) {
			case "passbook":
				printPassbook(request, response);
				break;
			case "deposite":
				depositeMoney(request, response);
				break;
			case "load":
				loadUserInformation(request, response);
				break;
			case "update":
				updateuserInformation(request, response);
				break;
			case "acInfo":
				fetchingAccountinfo(request, response);
				break;
			case "withdraw":
				withdrawMoney(request, response);
				break;
			case "filterByDate":
				filterTransactionByDate(request, response);
				break;

			}
		} else {
			response.sendRedirect("login.jsp");
		}

	}

	private void filterTransactionByDate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int acNo = Integer.parseInt(request.getParameter("acNo"));
		String fromDate = request.getParameter("fromDate");
		String toDate = request.getParameter("toDate");

		List<Passbook> transactions = userDao.filterTransactionByDate(acNo, fromDate, toDate);
		System.out.println(transactions.isEmpty());
		if (transactions.isEmpty()) {
			String msg2 = "Record not found !!";
			request.setAttribute("msg2", msg2);
		} else {
			request.setAttribute("transactions", transactions);
		}
	
		request.setAttribute("acNo", acNo);
		RequestDispatcher dispatcher = request.getRequestDispatcher("passbook-details.jsp");
		dispatcher.forward(request, response);

	}

	private void fetchingAccountinfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int acNo = Integer.parseInt(request.getParameter("acNo"));
		double balance = Double.parseDouble(request.getParameter("balance"));
		request.setAttribute("acNo", acNo);
		request.setAttribute("balance", balance);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-transaction.jsp");
		dispatcher.forward(request, response);

	}

	private void updateuserInformation(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int acNo = Integer.parseInt(request.getParameter("acNo"));
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
		String password = request.getParameter("password");

		User user = new User(acNo, email, mobile, password);
		System.out.println(user);
		userDao.updateUserInformation(user);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-dashboard.jsp");
		dispatcher.forward(request, response);

	}

	private void loadUserInformation(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int acNo = Integer.parseInt(request.getParameter("acNo"));
		User user = userDao.getUserDetail(acNo);
		request.setAttribute("user", user);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-edit-information.jsp");
		dispatcher.forward(request, response);
	}

	private void depositeMoney(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String transactionType = request.getParameter("command");
		int acNo = Integer.parseInt(request.getParameter("acNo"));
		double balance = Double.parseDouble(request.getParameter("balance"));

		double ammount = Double.parseDouble(request.getParameter("ammount"));
//		userDao.depositeAmmount(ammount);
		LocalDate transactionDate = LocalDate.now();
		String today = transactionDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		double updatedBalance = balance + ammount;
		Passbook passbook = new Passbook(acNo, today, transactionType, ammount, updatedBalance);
		userDao.depositeAmmount(passbook);
		request.setAttribute("acNo", acNo);
		request.setAttribute("balance", updatedBalance);

		RequestDispatcher dispatcher = request.getRequestDispatcher("user-dashboard.jsp");

		dispatcher.forward(request, response);

	}

	private void withdrawMoney(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String transactionType = request.getParameter("command");
		int acNo = Integer.parseInt(request.getParameter("acNo"));
		double balance = Double.parseDouble(request.getParameter("balance"));

		double ammount = Double.parseDouble(request.getParameter("ammount"));
		LocalDate transactionDate = LocalDate.now();
		String today = transactionDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		if (balance - ammount < 1000) {
			int minimum = 1000;
			String msg = "Minimum balance 1000 required";
			request.setAttribute("msg", msg);
			request.setAttribute("minimum", minimum);
			request.setAttribute("balance", balance);
			request.setAttribute("acNo", acNo);
			RequestDispatcher dispatcher = request.getRequestDispatcher("user-transaction.jsp");
			dispatcher.forward(request, response);

		} else {
			double updatedBalance = balance - ammount;
			Passbook passbook = new Passbook(acNo, today, transactionType, ammount, updatedBalance);
			userDao.withdrawAmmount(passbook);
			request.setAttribute("acNo", acNo);
			request.setAttribute("balance", updatedBalance);
			RequestDispatcher dispatcher = request.getRequestDispatcher("user-dashboard.jsp");
			dispatcher.forward(request, response);
		}

	}

	private void printPassbook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int acNo = Integer.parseInt(request.getParameter("acNo"));
		request.setAttribute("acNo", acNo);
//		System.out.println("inside print passbook"+ acNo);
		List<Passbook> transactions = userDao.getTransaction(acNo);
//		System.out.println(transactions);
		request.setAttribute("transactions", transactions);
		RequestDispatcher dispatcher = request.getRequestDispatcher("passbook-details.jsp");
		dispatcher.forward(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
