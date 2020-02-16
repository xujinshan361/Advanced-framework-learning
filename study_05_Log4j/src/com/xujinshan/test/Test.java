package com.xujinshan.test;

import org.apache.log4j.Logger;

/**
 * log4j学习:
 * 		由Apache 推出的开源免费日志处理的类库
 * 		为什么需要日志：
 * 			在项目中编写System.out.println() 输出到控制台，当项目发布到Tomcat后，没有控制台，不容易看到输出结果
 * 			log4j 作用，不仅能把内容输出到控制台，还能把内容输出到文件，便于观察
 * 		使用步骤：
 * 			导入log4j-xxx.jar 包
 * 			在src下新建log4.properties(路径和名称都不允许改变)
 * 			ConversionPattern:写表达式
 * 			log4j.appender.LOGFILE.File 文件位置及名称(日志文件扩展名为.log)
 * 		输出级别：
 * 			fatal(致命错误)> error(错误)>warn(警告)>info(普通信息)>debug(调试信息)
 * 		pattern 中常用表达式
 * 			%C		报名+类名
 * 			%d{YYYY-MM-dd HH:mm:ss}		时间
 * 			%L		行号
 * 			%m		信息
 * 			%n		换行
 * @author xujinshan361@163.com
 *
 */
public class Test {

	public static void main(String[] args) {
		Logger logger = Logger.getLogger(Test.class);
		logger.debug("这是一个调试信息");
		logger.info("普通信息");
		
		try {
			int index = 5/0;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
}
