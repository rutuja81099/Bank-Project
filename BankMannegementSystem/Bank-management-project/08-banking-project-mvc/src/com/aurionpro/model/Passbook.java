package com.aurionpro.model;


public class Passbook {

	private int srNo;
	private int acNo;
	private String transactionDate;
	private String transactionType;
	private double ammount;
	private double balance;

	public Passbook(int srNo, int acNo, String transactionDate, String transactionType, double ammount,
			double balance) {
		super();
		this.srNo = srNo;
		this.acNo = acNo;
		this.transactionDate = transactionDate;
		this.transactionType = transactionType;
		this.ammount = ammount;
		this.balance = balance;
	}

	public Passbook(int acNo, String transactionDate, String transactionType, double ammount,
			double updatedBalance) {
		this.acNo= acNo;
		this.transactionDate = transactionDate;
		this.transactionType = transactionType;
		this.ammount = ammount;
		this.balance = updatedBalance;
		
	}

	public int getSrNo() {
		return srNo;
	}

	public int getAcNo() {
		return acNo;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public double getAmmount() {
		return ammount;
	}

	public double getBalance() {
		return balance;
	}

	@Override
	public String toString() {
		return "Passbook [srNo=" + srNo + ", acNo=" + acNo + ", transactionDate=" + transactionDate
				+ ", transactionType=" + transactionType + ", ammount=" + ammount + ", balance=" + balance + "]";
	}

	
}
