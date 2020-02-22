package com.xujinshan.pojo;

import net.sf.cglib.proxy.Enhancer;

/**
 * cglib动态代理
 * 		优点：
 * 			基于字节码，生成真实对象的子类
 * 				运行效率高于JDK动态代理
 * 			不需要实现接口
 * 		缺点：
 * 			非JDK功能，需要导入额外jar
 * 				asm.jar   cglib.jar
 * 		使用spring aop时，只要出现Proxy和真实对象转换异常
 * 			设置为true，使用cglib	
 * 			设置为false，使用jdk(默认值)
 * 		<aop:aspectj-autoproxy proxy-target-class="true"></aop:aspectj-autoproxy>
 * @author xujinshan361@163.com
 *
 */
public class Women {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(LaoZong.class);
		enhancer.setCallback(new MiShu());
		LaoZong laozong = (LaoZong) enhancer.create();
		laozong.chifan();
	}
}
