package com.aurionpro.controller;

import java.io.IOException;
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

@WebServlet("/AdminController")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminController() {
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



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String command = request.getParameter("command");

		System.out.println(command);

		HttpSession session = request.getSession();
		
		Object admin = (Object) session.getAttribute("isAdmin");

		if(admin!=null ) {
			switch (command) {
			case "userDetail":
				listUsers(request, response);
				break;
			case "add":
				addUser(request, response);
				break;
			case "delete":
				deletuser(request, response);
				break;
			case "search":
				searchUser(request, response);
				break;
			case "filterByDate":
				filterTransactionByDate(request, response);
				break;
			case "passbookAdmin":
				printPassbookAdmin(request, response);
			case "searchadminpassbook":
				searchAdmin(request, response);
				break;
			case "logout":
				RequestDispatcher dispature = request.getRequestDispatcher("login.jsp");
				dispature.forward(request, response);
			}
		}else {
			response.sendRedirect("login.jsp");
		}
		

		
	}

	
	private void filterTransactionByDate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fromDate = request.getParameter("fromDate");
		String toDate = request.getParameter("toDate");

		List<Passbook> transactions = userDao.filterTransactionByDate(fromDate, toDate);
		System.out.println(transactions.isEmpty());
		if (transactions.isEmpty()) {
			String msg2 = "Record not found !!";
			request.setAttribute("msg2", msg2);
		} else {
			request.setAttribute("listPassbook", transactions);
		}
	
		RequestDispatcher dispatcher = request.getRequestDispatcher("admin-passbook-details.jsp");
		dispatcher.forward(request, response);		
	}



	private void printPassbookAdmin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		System.out.println("i am in admin passbook");
		List<Passbook> listPass = userDao.PassBook();
		System.out.println(listPass);
		request.setAttribute("listPassbook", listPass);
		RequestDispatcher dispatcher = request.getRequestDispatcher("admin-passbook-details.jsp");
		dispatcher.forward(request, response);

	}
	
	private void addUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int acNo = Integer.parseInt(request.getParameter("acno"));
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		String mobile = request.getParameter("mobile");
		int balance = Integer.parseInt(request.getParameter("balance"));
		
		User tempUser = new User(acNo, name, age, email, password, gender, mobile, balance);
		userDao.addUser(tempUser);
		
		listUsers(request, response);

	}
	
	private void deletuser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int acNo = Integer.parseInt(request.getParameter("acNo"));

		System.out.println("acno:" + acNo);

		userDao.deleteStudent(acNo);
		listUsers(request, response);
	}
	
	
	private void searchUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int acno = Integer.parseInt(request.getParameter("search"));

		List<User> user = userDao.searchUser(acno);

		System.out.println(user.isEmpty());
		if (user.isEmpty()) {
			String msg1 = "Record not found !!";
			request.setAttribute("msg1", msg1);
		} else {
			request.setAttribute("listUser", user);
			System.out.println("Searched students:" + user);// Outputs on console
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("user-details.jsp");
		dispatcher.forward(request, response);
	}
	
	
	
	private void searchAdmin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int acno = Integer.parseInt(request.getParameter("searchadmin"));
		System.out.println(acno);
//		if(acno==0) {
//			listUsers(request, response);
//		}
		
		List<Passbook> passbook = userDao.searchPassbookUser(acno);
		System.out.println(passbook);
		
		request.setAttribute("listPassbook", passbook);
		
//		if(passbook.isEmpty()) {
//			request.setAttribute("userList", null);
//		} else {
//			request.setAttribute("listPassbook", passbook);
//			System.out.println("Searched students:" +passbook);//Outputs on console
//		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("admin-passbook-details.jsp");
		dispatcher.forward(request, response);

	}
	
	
	private void listUsers(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<User> listUsers = userDao.listUsers();
		
		if (listUsers.isEmpty()) {
			request.setAttribute("userList", null);
		}
		System.out.println(listUsers);
		request.setAttribute("listUser", listUsers);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-details.jsp");
		dispatcher.forward(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
