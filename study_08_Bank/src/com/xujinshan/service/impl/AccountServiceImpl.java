package com.xujinshan.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import com.xujinshan.pojo.Account;
import com.xujinshan.pojo.Log;
import com.xujinshan.service.AccountService;

public class AccountServiceImpl implements AccountService{

	@Override
	public int transfer(Account accountIn, Account accountOut) throws IOException {
		InputStream is = Resources.getResourceAsStream("mybatis.xml");
		SqlSessionFactory factory= new SqlSessionFactoryBuilder().build(is);
		SqlSession session = factory.openSession();
		// 先判断账号和密码是否匹配
		Account accountOutSelect = session.selectOne("com.xujinshan.mapper.AccountMapper.selectByAccountAccnoAndPwd", accountOut);
		if(accountOutSelect !=null) {
			if(accountOutSelect.getBalance()>=accountOut.getBalance()) {
				Account accountInSelect= session.selectOne("com.xujinshan.mapper.AccountMapper.selectByAccountAccnoAndName", accountIn);
				if(accountInSelect !=null) {
					accountIn.setBalance(accountOut.getBalance());
					accountOut.setBalance(-accountOut.getBalance());
					int index = session.update("com.xujinshan.mapper.AccountMapper.updateBalanceByAccno",accountOut);
					index+=session.update("com.xujinshan.mapper.AccountMapper.updateBalanceByAccno",accountIn);
					if(index==2) {
						// 转账成功
						// 日志表记录
						Log log = new Log();
						log.setAccountOut(accountOut.getAccNo());
						log.setAccountIn(accountIn.getAccNo());
						log.setMoney(accountIn.getBalance());
						session.insert("com.xujinshan.mapper.LogMapper.insertLog",log);
						// 日志文件记录
						Logger logger = Logger.getLogger(AccountServiceImpl.class);
						logger.info(log.getAccountOut()+"给"+log.getAccountIn()+"在"+new Date().toString()+"转了"+log.getMoney());
						session.commit();
						session.close();
						return SUCCESS;
						
					}else {
						// 转账失败
						session.rollback();   // 设置回滚
						session.close();
						return ERROR;
					}
				}else {
					// 账号和姓名不匹配
					return ACCOUNT_NAME_NOT_MATCH;
				}
			}else {
				// 余额不足
				return ACCOUNT_BALANCE_NOT_ENOUGH;
			}
		}else {
			// 账号和密码不匹配
			return ACCOUNT_PASSWORD_NOT_MATCH;
		}
	}

}
