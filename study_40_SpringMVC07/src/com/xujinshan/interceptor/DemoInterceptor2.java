package com.xujinshan.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 拦截器
 * @author xujinshan361@163.com
 *
 */
public class DemoInterceptor2 implements HandlerInterceptor {
	//在进入控制器之前执行
	//如果返回值为false,阻止进入控制器
	//控制代码
	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		System.out.println("preHandle2");
		return true;
	}
	//控制器执行完成,进入到jsp之前执行.
	//日志记录.
	//敏感词语过滤
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		System.out.println("postHandle2");
	}
	//jsp执行完成后执行
	//记录执行过程中出现的异常.
	//可以把异常记录到日志中
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		System.out.println("afterCompletion2");
	}
}
