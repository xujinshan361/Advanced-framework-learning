package com.xujinshan.pojo;

public class LaoZong implements Gongneng{

	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Override
	public void zhidingxiaomubiao() {
		System.out.println("指定小目标");
	}
	public LaoZong(String name) {
		super();
		this.name = name;
	}

	public LaoZong() {
		super();
	}
	
}
