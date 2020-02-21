package com.xujinshan.advice;

import java.lang.reflect.Method;

import org.springframework.aop.ThrowsAdvice;

public class MyThrow implements ThrowsAdvice{

	public void afterThrowing(Exception ex) throws Throwable{
		System.out.println("执行异常通知-schema-base方式");
	}
	
	public void afterThrowing(Method m, Object[] args, Object target, Exception ex) throws Throwable{
		System.out.println("执行异常通知");
	}
}
