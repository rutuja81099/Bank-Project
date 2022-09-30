package com.aurionpro.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class UserDao {

	DataSource dataSource;

	public UserDao(DataSource dataSourse) {
		super();
		this.dataSource = dataSourse;
	}

	public List<User> listUsers() {

		Connection con = null;
		Statement stmt = null;
		ResultSet result = null;
		List<User> users = new ArrayList<>();

		try {
			con = dataSource.getConnection();
			String sql = "select * from user ;";
			stmt = con.createStatement();
			result = stmt.executeQuery(sql);
			while (result.next()) {
				int acNo = result.getInt("acNo");
				String name = result.getString("name");
				int age = result.getInt("age");
				String email = result.getString("email");
				String password = result.getString("password");
				String gender = result.getString("gender");
				String mobile = result.getString("mobile");
				int balance = result.getInt("balance");

				users.add(new User(acNo, name, age, email, password, gender, mobile, balance));

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, stmt, result);
		}
		return users;

	}

	public List<Passbook> getTransaction(int acNo) {

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		List<Passbook> transactions = new ArrayList<>();

		try {
			con = dataSource.getConnection();
			String sql = "select * from passbook where acNo=? ORDER BY srno desc;";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, acNo);
			result = stmt.executeQuery();

			while (result.next()) {
				int srNo = result.getInt("srNo");
				String transactionDate = result.getString("transactionDate");
				String transactionType = result.getString("transactionType");
				double ammount = result.getInt("ammount");
				double balance = result.getInt("balance");

				transactions.add(new Passbook(srNo, acNo, transactionDate, transactionType, ammount, balance));

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, stmt, result);
		}
		return transactions;
	}

	public void deleteStudent(int acNo) {
		Connection con = null;
		PreparedStatement stmt = null;

		try {
			String sql = "delete from user where acNo=?;";
			con = dataSource.getConnection();
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, acNo);
			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con = dataSource.getConnection();
			String sql = "delete from passbook where acNo=?;";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, acNo);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		finally {
			close(con, stmt, null);
		}
	}

	private void close(Connection con, Statement stmt, ResultSet result) {

		try {
			if (con != null) {
				con.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (result != null) {
				result.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addUser(User tempUser) {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = dataSource.getConnection();
			String sql = "insert into user (acno,name,age,email,password,gender,mobile,balance) values(?,?,?,?,?,?,?,?);";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, tempUser.getAcNo());
			stmt.setString(2, tempUser.getName());
			stmt.setInt(3, tempUser.getAge());
			stmt.setString(4, tempUser.getEmail());
			stmt.setString(5, tempUser.getPassword());
			stmt.setString(6, tempUser.getGender());
			stmt.setString(7, tempUser.getMobile());
			stmt.setInt(8, (int) tempUser.getBalance());
			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		LocalDate transactionDate = LocalDate.now();
		String today = transactionDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		try {
			con = dataSource.getConnection();
			String sql = "insert into passbook (acno,transactionDate, transactionType, ammount, balance) values(?,?,?,?,?);";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, tempUser.getAcNo());
			stmt.setString(2, today);
			stmt.setString(3, "deposite");
			stmt.setDouble(4, tempUser.getBalance());
			stmt.setDouble(5, tempUser.getBalance());
			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(con, stmt, null);
		}
	}

	public List<User> searchUser(int acno) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		List<User> user = new ArrayList<>();
		try {
			conn = dataSource.getConnection();
			String sql = "select * from user where acno like ? ;";
			String query = "%" + acno + "%";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, query);
			result = stmt.executeQuery();
			while (result.next()) {
				int acNo = result.getInt("acno");
				String name = result.getString("name");
				int age = result.getInt("age");
				String email = result.getString("email");
				String password = result.getString("password");
				String gender = result.getString("gender");
				String mobile = result.getString("mobile");
				int balance = result.getInt("balance");
				user.add(new User(acNo, name, age, email, password, gender, mobile, balance));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, stmt, result);
		}
		return user;

	}

	public List<Passbook> PassBook() {
		Connection con = null;
		Statement stmt = null;
		ResultSet result = null;
		List<Passbook> userPassbook = new ArrayList<>();

		try {
			con = dataSource.getConnection();
			String sql = "select * from passbook ORDER BY srno desc;";
			stmt = con.createStatement();
			result = stmt.executeQuery(sql);
			while (result.next()) {
				int srNo = result.getInt("srno");
				int acNo = result.getInt("acno");
				String transactionDate = result.getString("transactionDate");
				String transactionType = result.getString("transactionType");
				double ammount = result.getInt("ammount");
				double balance = result.getInt("balance");
				userPassbook.add(new Passbook(srNo, acNo, transactionDate, transactionType, ammount, balance));

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, stmt, result);
		}
		return userPassbook;

	}

	public List<Passbook> searchPassbookUser(int acno) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		List<Passbook> passbook = new ArrayList<>();
		try {
			conn = dataSource.getConnection();
			String sql = "select * from passbook where acno like ? order by srno desc ;";
			String query = "%" + acno + "%";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, query);
			result = stmt.executeQuery();
			while (result.next()) {
				int srNo = result.getInt("srno");
				int acNo = result.getInt("acno");
				String transactionDate = result.getString("transactionDate");
				String transactionType = result.getString("transactionType");
				double ammount = result.getInt("ammount");
				double balance = result.getInt("balance");
				passbook.add(new Passbook(srNo, acNo, transactionDate, transactionType, ammount, balance));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, stmt, result);
		}
		return passbook;
	}

	public void depositeAmmount(double ammount) {

	}

	public User getUserDetail(int acNo) {
		PreparedStatement stmt = null;
		Connection con = null;
		ResultSet result = null;
		User user = null;

		try {
			con = dataSource.getConnection();
			String sql = "SELECT * FROM user WHERE acno=?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, acNo);
			result = stmt.executeQuery();

			if (result.next()) {
				String email = result.getString("email");
				String mobile = result.getString("mobile");
				String password = result.getString("password");
				user = new User(acNo, email, mobile, password);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	public void updateUserInformation(User user) {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = dataSource.getConnection();
			String sql = "update user set email=?,mobile=?,password=? where acNo=?;";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, user.getEmail());
			stmt.setString(2, user.getMobile());
			stmt.setString(3, user.getPassword());
			stmt.setInt(4, user.getAcNo());
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, stmt, null);
		}

	}
//
//	public void depositeAmmount(int acNo, double ammount) {
//		
//		
//
//	}

	public void depositeAmmount(Passbook passbook) {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = dataSource.getConnection();
			String sql = "update user set balance=? where acNo=?;";
			stmt = con.prepareStatement(sql);
			stmt.setDouble(1, passbook.getBalance());
			stmt.setInt(2, passbook.getAcNo());
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			con = dataSource.getConnection();
			String sql = "insert into passbook (acno,transactionDate, transactionType, ammount, balance) values(?,?,?,?,?);";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, passbook.getAcNo());
			stmt.setString(2, passbook.getTransactionDate());
			stmt.setString(3, passbook.getTransactionType());
			stmt.setDouble(4, passbook.getAmmount());
			stmt.setDouble(5, passbook.getBalance());
			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			close(con, stmt, null);
		}

	}

	public void withdrawAmmount(Passbook passbook) {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = dataSource.getConnection();
			String sql = "update user set balance=? where acNo=?;";
			stmt = con.prepareStatement(sql);
			stmt.setDouble(1, passbook.getBalance());
			stmt.setInt(2, passbook.getAcNo());
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			con = dataSource.getConnection();
			String sql = "insert into passbook (acno,transactionDate, transactionType, ammount, balance) values(?,?,?,?,?);";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, passbook.getAcNo());
			stmt.setString(2, passbook.getTransactionDate());
			stmt.setString(3, passbook.getTransactionType());
			stmt.setDouble(4, passbook.getAmmount());
			stmt.setDouble(5, passbook.getBalance());
			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			close(con, stmt, null);
		}

	}

	public List<Passbook> filterTransactionByDate(int acNo, String fromDate, String toDate) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		System.out.println(acNo+" "+ fromDate+" "+toDate);
		List<Passbook> transactions = new ArrayList<>();
		try {
			conn = dataSource.getConnection();
			String sql = "select * from passbook where transactionDate between ? and ? and acNo=?;";
//			String sql =" select * from passbook where transactionDate between  Date('2022-09-25') and Date('2022-09-26') and acNo=101";
			
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, fromDate);
			stmt.setString(2, toDate);
			stmt.setInt(3, acNo);
			result = stmt.executeQuery();
			while(result.next()) {
				int srNo = result.getInt("srno");
				String transactionDate = result.getString("transactionDate");
				String transactionType = result.getString("transactionType");
				double ammount = result.getInt("ammount");
				double balance = result.getInt("balance");
				transactions.add(new Passbook(srNo, acNo, transactionDate, transactionType, ammount, balance));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(conn, stmt, result);
		}
		return transactions;
	}

	public List<Passbook> filterTransactionByDate(String fromDate, String toDate) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		List<Passbook> transactions = new ArrayList<>();
		try {
			conn = dataSource.getConnection();
			String sql = "select * from passbook where transactionDate between ? and ?;";			
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, fromDate);
			stmt.setString(2, toDate);
			result = stmt.executeQuery();
			while(result.next()) {
				int srNo = result.getInt("srno");
				int acNo = result.getInt("acNo");
				String transactionDate = result.getString("transactionDate");
				String transactionType = result.getString("transactionType");
				double ammount = result.getInt("ammount");
				double balance = result.getInt("balance");
				transactions.add(new Passbook(srNo, acNo, transactionDate, transactionType, ammount, balance));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(conn, stmt, result);
		}
		return transactions;
	}

}
