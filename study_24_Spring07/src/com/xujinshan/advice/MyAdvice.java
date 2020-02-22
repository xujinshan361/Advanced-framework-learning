package com.xujinshan.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAdvice {

	@Before("com.xujinshan.test.Demo.demo()")
	public void myBefore() {
		System.out.println("前置");
	}
	@After("com.xujinshan.test.Demo.demo()")
	public void myAfter() {
		System.out.println("后置通知");
	}
	@AfterThrowing("com.xujinshan.test.Demo.demo()")
	public void myThrowing() {
		System.out.println("异常通知");
	}
	@Around("com.xujinshan.test.Demo.demo()")
	public Object myArround(ProceedingJoinPoint p) throws Throwable {
		System.out.println("环绕- 前置");
		Object result = p.proceed();
		System.out.println("环绕-后置");
		return result;
	}
}
