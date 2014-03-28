package cn.slimsmart.common.document.excel.bean;

import java.util.Date;

import cn.slimsmart.common.document.excel.annotation.Cell;
import cn.slimsmart.common.document.excel.annotation.Excel;
import cn.slimsmart.common.document.excel.annotation.support.Types;

@Excel(title="学生信息")
public class User {
	
	@Cell(display="姓名",index=0)
	private String name;
	
	@Cell(display="成绩",index=1,length=2,type=Types.POJO)
	private Score score;
	
	@Cell(display="年龄",index=3,type=Types.INTEGER)
	private int age;
	
	@Cell(display="成绩",index=4,length=2,type=Types.POJO)
	private Score score1;
	
	@Cell(display="生日",index=6,type=Types.DATE,dataFormat="yyyy-MM-dd HH:mm:ss")
	private Date birthday;
	
	@Cell(display="成绩",index=7,length=2,type=Types.POJO)
	private Score score2;
	
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

	public Score getScore2() {
		return score2;
	}

	public void setScore2(Score score2) {
		this.score2 = score2;
	}
}
