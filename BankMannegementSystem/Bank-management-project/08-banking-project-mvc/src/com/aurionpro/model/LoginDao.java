package com.aurionpro.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

public class LoginDao {

	private DataSource dataSource;

	public LoginDao(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	public Admin checkAdminCredentials(Admin admin) {

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		Admin isAdmin = null;
		try {
			con = dataSource.getConnection();
			String sql = "select * from admin where email=? and password=?;";
			stmt = con.prepareStatement(sql);

			stmt.setString(1, admin.getEmail());
			stmt.setString(2, admin.getPassword());

			result = stmt.executeQuery();

			if (result.next()) {
				String email = result.getString("email");
				String password = result.getString("password");
				isAdmin = new Admin(email, password);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(con, stmt, result);
		}

		return isAdmin;
	}

	public User checkUserCredentials(User user) {

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		User isUser = null;
		try {
			con = dataSource.getConnection();
			String sql = "select * from user where email=? and password=?;";
			stmt = con.prepareStatement(sql);

			stmt.setString(1, user.getEmail());
			stmt.setString(2, user.getPassword());

			result = stmt.executeQuery();
			if (result.next()) {
				String email = result.getString("email");
				String password = result.getString("password");
				int acNo = result.getInt("acNo");
				double balance = result.getDouble("balance");
				isUser = new User(acNo, email, password , balance);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(con, stmt, result);
		}

		return isUser;
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

}
