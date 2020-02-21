package com.xujinshan.advice;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class MyBeforeAdvice implements MethodBeforeAdvice{
	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		System.out.println("切点方法对象："+method.getName());
		if(args!=null&&args.length>0) {
			System.out.println("方法参数：" + args);
		}else {
			System.out.println("切点没有参数");
		}
		System.out.println("对象：" + target);
		System.out.println("执行前置通知");
	}
}
