package com.xujinshan.pojo;

public class People {

	private int id;
	private String name;
	@Override
	public String toString() {
		return "People [id=" + id + ", name=" + name + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public People() {
		super();
		System.out.println("执行创建对象");
	}
	public People(int id, String name) {
		super();
		this.id = id;
		this.name = name;
		System.out.println("执行有参构造方法");
	}
	public People(String name, int id) {
		super();
		this.id = id;
		this.name = name;
		System.out.println("执行有参构造方法2");
	}
	public People(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
		System.out.println("执行有参构造方法3");
	}
}
