package com.aurionpro.model;

public class User {

	private int acNo;
	private String name;
	private int age;
	private String email;
	private String password;
	private String gender;
	private String mobile;
	private double balance;

	public User(int acNo, String name, int age, String email, String password, String gender, String mobile,
			double balance) {
		super();
		this.acNo = acNo;
		this.name = name;
		this.age = age;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.mobile = mobile;
		this.balance = balance;
	}

	public User(String email, String password) {
		this.email= email;
		this.password = password;
	}

//	public User(int acNo, String email, String password) {
//		this.acNo = acNo;
//		this.email = email;
//		this.password = password;
//	}


	public User(int acNo, String email, String mobile, String password) {
		this.acNo = acNo;
		this.email = email;
		this.mobile= mobile;
		this.password = password;
	}

	public User(int acNo, String email, String password, double balance) {
		this.acNo = acNo;
		this.email = email;
		this.password = password;
		this.balance = balance;
		
	}

	public int getAcNo() {
		return acNo;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getGender() {
		return gender;
	}

	public String getMobile() {
		return mobile;
	}

	public double getBalance() {
		return balance;
	}

	@Override
	public String toString() {
		return "User [acNo=" + acNo + ", name=" + name + ", age=" + age + ", email=" + email + ", password=" + password
				+ ", gender=" + gender + ", mobile=" + mobile + ", balance=" + balance + "]";
	}

}
