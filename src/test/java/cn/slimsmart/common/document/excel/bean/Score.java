package cn.slimsmart.common.document.excel.bean;

import cn.slimsmart.common.document.excel.annotation.Cell;
import cn.slimsmart.common.document.excel.annotation.support.Types;

public class Score {
	
	@Cell(display="数学",index=3,type=Types.INTEGER)
	private int math;
	@Cell(display="英语",index=4,type=Types.INTEGER)
	private int english;
	
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
}
