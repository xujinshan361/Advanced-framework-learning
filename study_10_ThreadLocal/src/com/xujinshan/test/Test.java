package com.xujinshan.test;


/**
 * ThreadLocal：	
 * 		线程容：，给线程保定一个Object内容，后只要线程不变，可以随时取出，
 * 		改变线程，无法取出
 * @author xujinshan361@163.com
 *
 */
public class Test {

	public static void main(String[] args) {
		final ThreadLocal<String> threadLocal = new ThreadLocal<>(); 
		threadLocal.set("测试");
		
		new Thread() {
			public void run() {
				String result = threadLocal.get();
				System.out.println("结果为："+result);
			};
		}.start();
		String result = threadLocal.get();
		System.out.println(result);
	}
}
