package com.xujinshan.controller;


import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

/**
 *文件上传
 *		基于Apache的commons-fileupload.jar 完成文件上传
 *		MultipartResovler作用：
 *			把客户端上传的文件流转换成MutipartFile 封装类
 *			通过MutipartFile 封装类获取到文件流
 *
 *		表单数据类型分类
 *			在<form> 的enctype 属性控制表单类型
 *			 默认值 application/x-www-form-urlencoded,普通表单数据.(少量文字信息)
 *			text/plain 大文字量使用的类型，邮件，论文
 *			multipart/form-data  表单中包含二进制文件内容
 *		实现步骤
 *			导入springmvc包和Apache 文件上传commons-fileupload和commons-io 俩个jar
 *			编写JSP文件
 *			配置sprigmvc.xml
 *			编写控制器类
 *				 MultipartFile 对象名必须和<input type=”file”/>的 name 属性值相同
 * @author xujinshan361@163.com
 *
 */
@Controller
public class DemoController {
	@RequestMapping("upload")
	public String upload(String name,MultipartFile file) throws IOException {
		System.out.println("name:"+name);
		String fileName = file.getOriginalFilename();
		String suffix = fileName.substring(fileName.lastIndexOf("."));
		//判断上传文件类型
		if(suffix.equalsIgnoreCase(".png")){
			String uuid = UUID.randomUUID().toString();
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File("E:/"+uuid+suffix));
			return "/index.jsp";
		}else{
			return "/error.jsp";
		}
	}
}
