package com.xujinshan.test;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
public class Demo {

	@Pointcut("execution(* com.xujinshan.test.Demo.demo())")
	public void demo()throws Exception{
//		int a = 1/0;
		System.out.println("demo");
	}
	
}
