package cn.slimsmart.common.document.excel.bean;

import cn.slimsmart.common.document.excel.annotation.Cell;
import cn.slimsmart.common.document.excel.annotation.Excel;
import cn.slimsmart.common.document.excel.annotation.support.Types;

@Excel(title="信息")
public class Info {
	
	@Cell(display="课时",index=0,type=Types.INTEGER,selectTmplData="aaa;bbb;ccc")
	private int time;
	@Cell(display="老师",index=1,type=Types.STRING,cellTipMsg="输入汉字")
	private String teacher;
	
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	
	

}
