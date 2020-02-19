package com.xujinshan.pojo;

public class PeopleFactory {

	public People createPeople(String witch) {
		switch(witch) {
		case "A":
			// 之前处理简单逻辑
			return new A();
		case "B":
			return new B();
		default:
			return null;
		}
	}
	public People createPeople() {
		return new People();
	}
	public People newInstance() {
		return new People(1,"测试");
	}
	public static People newInstance2() {
		return new People(1,"测试");
	}
}
