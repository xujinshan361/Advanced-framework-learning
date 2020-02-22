package com.xujinshan.pojo;

import java.lang.reflect.Proxy;

/**
 * 动态代理
 * 		为了解决静态代理频繁编写代理功能缺点
 * 		分类：
 * 			JDK提供
 * 			cglib 动态代理
 * JDK动态代理
 * 		和cglib 动态代理对比：
 * 			优点：
 * 				真实对象必须实现接口
 * 				利用反射机制，效率不高
 * 		使用JDK动态代理时可能出现异常
 * 			出现原因：希望把接口对象转换为具体真实对象
 * 			
 * @author xujinshan361@163.com
 *
 */
public class Women {
	public static void main(String[] args) {
		MiShu mishu = new MiShu();
		// 第一个参数：反射时使用的类加载器
		// 第二个参数：Proxy 需要实现什么接口
		// 第三个参数：通过接口对象调用方法是，需要调用哪个类 invoke方法
		Gongneng gongneng = (Gongneng) Proxy.newProxyInstance(Women.class.getClassLoader(), new Class[] {Gongneng.class}, null);
		gongneng.chifan();
	}
}
