package com.xujinshan.advice;

import org.aspectj.lang.ProceedingJoinPoint;

public class MyAdvice {

	public void myBefore(String name, int age) {
		System.out.println("前置:"+name+"  "+age);
	}
	public void myBefore1(String name) {
		System.out.println("前置；"+name);
	}
	public void myAfter() {
		System.out.println("后置1");
	}
	public void myAfterReturning() {
		System.out.println("后置2");
	}
	public void myThrowing() {
		System.out.println("异常");
	}
	public Object myArround(ProceedingJoinPoint p) throws Throwable {
		System.out.println("执行环绕-前置");
		Object result = p.proceed();
		System.out.println("执行环绕-后置");
		return result;
	}
	
}
