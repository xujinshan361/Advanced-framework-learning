package com.xujinshan.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xujinshan.pojo.Menu;
import com.xujinshan.service.MenuService;

@Controller
public class MenuController {
	/**
	 * springmvc容器调用spring容器中内容
	 */
	@Resource
	private MenuService menuServiceImpl;
	
	@RequestMapping("show")
	@ResponseBody
	public List<Menu> show(){
		return menuServiceImpl.show();
	}
}
