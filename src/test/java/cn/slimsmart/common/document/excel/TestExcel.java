package cn.slimsmart.common.document.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import cn.slimsmart.common.document.excel.bean.Score;
import cn.slimsmart.common.document.excel.bean.User;
import cn.slimsmart.common.util.date.DateUtil;

public class TestExcel extends TestCase{
	
	private String path = null;
	
	@Before
	public void setUp(){
		path = "src/test/java/"+TestExcel.class.getPackage().getName().replaceAll(".", "/")+"/";
	}
	
	@Test
	public void testExportExcel() throws IOException{
		File file = new File(path+User.class.getSimpleName()+".xls");
		FileOutputStream out = new FileOutputStream(file);
		ExportExcel<User> user = new ExportExcel<User>(User.class);
		List<User> list = new ArrayList<User>();
		User u = new User();
		u.setName("李四");
		u.setBirthday(new Date());
		u.setAge(20);
		Score score = new Score();
		score.setEnglish(80);
		score.setMath(90);
		u.setScore(score);
		list.add(u);
		
		User u1 = new User();
		u1.setName("小三");
		u1.setBirthday(DateUtil.addDays(new Date(), -100));
		u1.setAge(35);
		Score score1 = new Score();
		score1.setEnglish(99);
		score1.setMath(100);
		u1.setScore(score1);
		list.add(u1);
		
		User u2 = new User();
		u2.setName("小雨");
		u2.setBirthday(DateUtil.addDays(new Date(), -1000));
		u2.setAge(32);
		Score score2 = new Score();
		score2.setEnglish(99);
		score2.setMath(97);
		u2.setScore(score2);
		list.add(u2);
		
		user.exportExcel(list, out);
	}
}