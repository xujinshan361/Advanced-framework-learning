package com.xujinshan.pojo;

/**
 * 代理设计模式
 * 		设计模式：前人总结的一套解决特定问题的代码
 * 		代理设计模式优点：
 * 			保护真实对象
 * 			让真实对象职责更明确
 * 			扩展
 * 		代理设计模式
 * 			真实对象(老总)
 * 			代理对象(秘书)
 * 			抽象对象(抽象功能),谈小目标
 * 静态代理模式：
 * 		由代理对象代理所有真实对象的功能
 * 			自己编写代理类
 * 			把每个代理的功能需要单独编写
 * 		静态代理设计模式的缺点：
 * 			当代理功能比较多时，代理类中方法需要写很多
 * @author xujinshan361@163.com
 *
 */


public class Women {

	public static void main(String[] args) {
		MiShu mishu = new MiShu();
		mishu.zhidingxiaomubiao();
	}
}
