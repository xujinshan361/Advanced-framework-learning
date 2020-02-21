package com.xujinshan.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ValidCodeServlet
 */
@WebServlet("/validCode")
public class ValidCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 创建一张图片
		// 单位是像素
		BufferedImage image = new BufferedImage(200, 100, BufferedImage.TYPE_INT_RGB);
		// 透明的玻璃
		// 向画板上画内容必须先设置画笔
		Graphics2D gra = image.createGraphics();
		gra.setColor(Color.WHITE);
		// 从哪个坐标开始填充，后俩个参数，矩形区域
		gra.fillRect(0, 0, 200, 100);
		ServletOutputStream outputStream = resp.getOutputStream();
		List<Integer> randList = new ArrayList<Integer>();
		Random random = new Random();
		for(int i=0; i<4; i++) {
			randList.add(random.nextInt(10));
		}
		
		// 设置字体--加粗倾斜
		Color[] colors = new Color[] {Color.RED, Color.YELLOW, Color.BLUE, Color.GREEN, Color.PINK, Color.GRAY};
		
		gra.setFont(new Font("宋体", Font.BOLD|Font.ITALIC, 40));
		for(int i=0;i<randList.size(); i++) {
			//设置画笔颜色
			gra.setColor(colors[random.nextInt(colors.length)]);
			// 设置字体位置
			gra.drawString(randList.get(i)+"", i*40, 70+(random.nextInt(21)-10));
		}
		for(int i=0;i<2;i++) {
			gra.setColor(colors[random.nextInt(colors.length)]);
			// 画横线
			gra.drawLine(0,random.nextInt(101),200,random.nextInt(101));
			
		}
		// 工具类
		ImageIO.write(image,"jpg",outputStream);
		
		// 把验证码放到session中
		HttpSession session = req.getSession();
		session.setAttribute("code", ""+randList.get(0)+randList.get(1)+randList.get(2)+randList.get(3));
	}
}
