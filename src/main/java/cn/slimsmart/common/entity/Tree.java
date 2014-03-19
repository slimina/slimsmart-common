package cn.slimsmart.common.entity;

import java.util.List;

public abstract class Tree<T> extends BaseSerializable{
	
	private static final long serialVersionUID = 1L;
	
	private List<T> children;

	public List<T> getChildren() {
		return children;
	}

	public void setChildren(List<T> children) {
		this.children = children;
	}
}
