package com.xujinshan.service;

import java.io.IOException;

import com.xujinshan.pojo.Account;

public interface AccountService {

	/**
	 * 账号和密码不匹配状态码
	 */
	int ACCOUNT_PASSWORD_NOT_MATCH = 1;
	/**
	 * 账号余额不足
	 */
	int ACCOUNT_BALANCE_NOT_ENOUGH = 2;
	/**
	 * 账号和姓名不匹配
	 */
	int ACCOUNT_NAME_NOT_MATCH = 3;
	/**
	 * 转账失败
	 */
	int ERROR = 4;
	/**
	 * 转账成功
	 */
	int SUCCESS = 5;
	/**
	 * 转账
	 * @param accountIn 收款账户
	 * @param accountOut 转账账户
	 * @return
	 * @throws IOException 
	 */
	int transfer(Account accountIn, Account accountOut) throws IOException;
}
