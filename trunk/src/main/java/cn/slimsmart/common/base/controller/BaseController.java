package cn.slimsmart.common.base.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.slimsmart.common.base.model.Page;

/**
 * 控制层基类
 * 
 * @author zhutianwei
 * 
 * @param <T>
 */
public abstract class BaseController<T> {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;
	
	protected Map<String,Object> filterMap = null;

	protected Page<T> pageItem;
	protected List<T> resultList;
	protected T entity;
	
	protected BaseController(){
	}
	

	public abstract Page<T> list(Page<T> pageItem) throws Exception;

	public abstract String edit(T entity) throws Exception;

	public abstract T detail(String id) throws Exception;

	public abstract String save(T entity) throws Exception;

	public abstract String delete(String id) throws Exception;

	public void sendRedirect(String url) throws IOException {
		response.sendRedirect(url);
	}

	public List<T> getResultList() {
		return resultList;
	}


	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}

	
	public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}
}
