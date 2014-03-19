package cn.slimsmart.common.entity;

import java.util.Collection;

/**
 * 分页返回对象
 * 
 * @author Zhu.TW
 * 
 */
public class Pagination<T> extends BaseSerializable{

	private static final long serialVersionUID = 1L;

	
	private Collection<T> data;
	
	private int count;

	public Collection<T> getData() {
		return data;
	}

	public void setData(Collection<T> data) {
		this.data = data;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	
}
