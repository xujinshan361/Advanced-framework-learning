package com.xujinshan.advice;

public class MyThrowAdvice {
	public void myexecption(Exception e) {
		System.out.println("执行异常通知，异常message："+e.getMessage());
	}
}
