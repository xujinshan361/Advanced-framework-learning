package com.xujinshan.pojo;

public class Account {

	// 编号
	private int id;
	// 账号
	private String accNo;
	// 密码
	private int password;
	// 用户名
	private String name;
	// 账号余额
	private double balance;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccNo() {
		return accNo;
	}
	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}
	public int getPassword() {
		return password;
	}
	public void setPassword(int password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public Account(int id, String accNo, int password, String name, double balance) {
		super();
		this.id = id;
		this.accNo = accNo;
		this.password = password;
		this.name = name;
		this.balance = balance;
	}
	public Account() {
		super();
	}
	
}
