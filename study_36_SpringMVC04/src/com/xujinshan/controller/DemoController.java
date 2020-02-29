package com.xujinshan.controller;


import java.io.File;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 文件下载	
 * 		访问资源时响应头如果没有设置Content-Disposition,浏览器默认按照inline 值进行处理
 * 			inline 能显示就显示，不能显示就下载
 * 		只需要修改响应头中 Content-Disposition="attachment;filename=文件名"
 * 			attachment  下载，以附加形式下载
 * 			filename= 值，就是下载时显示的下载文件名
 * 		
 * 		实现步骤
 * 			导入Apache 的俩个包
 * 				commons-fileupload.jar
 * 				commons-io.jar
 *			在jsp中添加超链接，设置要下载文件
 *				在springmvc 中放行静态资源files文件夹
 *			编写控制器方法
 * @author xujinshan361@163.com
 *
 */
@Controller
public class DemoController {
	@RequestMapping("download")
	public void download(String fileName, HttpServletRequest req, HttpServletResponse resp) throws IOException {
//		PrintWriter out = resp.getWriter(); //输出一句话
//		resp.setContentType();
		// 设置响应头中文件下载
		resp.setHeader("Content-Disposition", "attachment;filename="+fileName);
		// 把二进制流放入响应体重
		ServletOutputStream os = resp.getOutputStream();
		File files = new File(req.getServletContext().getRealPath("files"),fileName);
		byte[] bytes= FileUtils.readFileToByteArray(files);
		os.write(bytes);
		os.flush();
		os.close();
	}
}
