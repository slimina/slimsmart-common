package cn.slimsmart.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Filter 抽象类，提供过滤前和后的处理方法
 * @author Zhu.TW
 *
 */
public abstract class AbstractFilter implements Filter{

	public abstract void beforeFilter(ServletRequest request, ServletResponse response);
	
	public abstract void afterFilter(ServletRequest request, ServletResponse response);
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		beforeFilter(request,response);
		chain.doFilter(request, response);
		afterFilter(request,response);
	}

}
