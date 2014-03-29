package cn.slimsmart.common.document.excel.bean;

import java.util.Date;

import cn.slimsmart.common.document.excel.annotation.Cell;
import cn.slimsmart.common.document.excel.annotation.Excel;
import cn.slimsmart.common.document.excel.annotation.support.Types;

@Excel(title="学生信息")
public class User {
	
	@Cell(display="姓名",index=0)
	private String name;
	
	@Cell(display="成绩",index=1,length=4,type=Types.POJO)
	private Score score;
	
	@Cell(display="年龄",index=5,type=Types.INTEGER)
	private int age;
	
	@Cell(display="成绩",index=6,length=4,type=Types.POJO)
	private Score score1;
	
	@Cell(display="生日",index=10,type=Types.DATE,dataFormat="yyyy-MM-dd HH:mm:ss")
	private Date birthday;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Score getScore() {
		return score;
	}

	public void setScore(Score score) {
		this.score = score;
	}

	public Score getScore1() {
		return score1;
	}

	public void setScore1(Score score1) {
		this.score1 = score1;
	}
}
