package cn.slimsmart.common.document.excel.bean;

import cn.slimsmart.common.document.excel.annotation.Cell;
import cn.slimsmart.common.document.excel.annotation.support.Types;

public class Score {
	
	@Cell(display="数学",index=0,type=Types.INTEGER)
	private int math;
	@Cell(display="英语",index=1,type=Types.INTEGER)
	private int english;
	@Cell(display="详情",index=2,length=2,type=Types.POJO)
	private Info info;
	
	public int getMath() {
		return math;
	}
	public void setMath(int math) {
		this.math = math;
	}
	public int getEnglish() {
		return english;
	}
	public void setEnglish(int english) {
		this.english = english;
	}
	public Info getInfo() {
		return info;
	}
	public void setInfo(Info info) {
		this.info = info;
	}
}
