package com.xujinshan.pojo;

public class Log {
	private int id;
	private String accountOut;
	private String accountIn;
	private Double money;
	public Log(int id, String accountOut, String accountIn, Double money) {
		super();
		this.id = id;
		this.accountOut = accountOut;
		this.accountIn = accountIn;
		this.money = money;
	}
	public Log() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccountOut() {
		return accountOut;
	}
	public void setAccountOut(String accountOut) {
		this.accountOut = accountOut;
	}
	public String getAccountIn() {
		return accountIn;
	}
	public void setAccountIn(String accountIn) {
		this.accountIn = accountIn;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
}
