package com.xujinshan.pojo;

public class MiShu implements Gongneng{

	private LaoZong  laozong = new LaoZong("云云");
	
	@Override
	public void zhidingxiaomubiao() {
		System.out.println("请问你要找马总吗？");
		laozong.zhidingxiaomubiao();
		System.out.println("把访客信息备份");
	}
} 
