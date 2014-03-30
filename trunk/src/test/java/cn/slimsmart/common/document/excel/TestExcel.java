package cn.slimsmart.common.document.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Before;
import org.junit.Test;

import cn.slimsmart.common.document.excel.bean.Info;
import cn.slimsmart.common.document.excel.bean.Score;
import cn.slimsmart.common.document.excel.bean.User;
import cn.slimsmart.common.document.excel.support.ExcelType;
import cn.slimsmart.common.util.common.date.DateUtil;
import cn.slimsmart.common.util.document.excel.ExcelUtil;

public class TestExcel extends TestCase {

	private String path = null;

	@Before
	public void setUp() {
		path = "src/test/java/" + TestExcel.class.getPackage().getName().replaceAll(".", "/") + "/";
	}

	@Test
	public void testExportExcel() throws IOException {
		File file = new File(path + User.class.getSimpleName() + ".xlsx");
		FileOutputStream out = new FileOutputStream(file);
		ExportExcel<User> user = new ExportExcel<User>(User.class);
		List<User> list = new ArrayList<User>();
		User u = new User();
		u.setName("李四");
		u.setBirthday(new Date());
		u.setAge(20);
		Score score = new Score();
		score.setEnglish(1);
		score.setMath(1);
		Info info = new Info();
		info.setTeacher("AAA");
		info.setTime(11);
		score.setInfo(info);
		u.setScore(score);
		score = new Score();
		score.setEnglish(2);
		score.setMath(2);
		info = new Info();
		info.setTeacher("bbb");
		info.setTime(222);
		score.setInfo(info);
		u.setScore1(score);
		list.add(u);

		User u1 = new User();
		u1.setName("小三");
		u1.setBirthday(DateUtil.addDays(new Date(), -100));
		u1.setAge(35);
		Score score1 = new Score();
		score1.setEnglish(1);
		score1.setMath(1);
		info = new Info();
		info.setTeacher("cccc");
		info.setTime(33333);
		score1.setInfo(info);
		u1.setScore(score1);
		score1 = new Score();
		score1.setEnglish(2);
		score1.setMath(2);
		info = new Info();
		info.setTeacher("dddd");
		info.setTime(4444);
		score1.setInfo(info);
		u1.setScore1(score1);
		list.add(u1);

		User u2 = new User();
		u2.setName("小雨");
		u2.setBirthday(DateUtil.addDays(new Date(), -1000));
		u2.setAge(32);
		Score score2 = new Score();
		score2.setEnglish(1);
		score2.setMath(1);
		info = new Info();
		info.setTeacher("eeee");
		info.setTime(5555);
		score2.setInfo(info);
		u2.setScore(score2);
		score2 = new Score();
		score2.setEnglish(2);
		score2.setMath(2);
		info = new Info();
		info.setTeacher("ffff");
		info.setTime(66666);
		score2.setInfo(info);
		u2.setScore1(score2);
		list.add(u2);

		user.exportExcel(list, out, ExcelType.EXCEL_2007);
	}

	public void testExcelSelect() throws IOException {
		Workbook wb = ExcelUtil.getWorkbook(ExcelType.EXCEL_2007);
		// excel文件对象
		Sheet sheetlist = wb.createSheet("sheetlist");// 工作表对象
		FileOutputStream out = new FileOutputStream("d:\\success.xlsx");
		String[] textlist = { "列表1", "列表2", "列表3", "列表4", "列表5" };
		ExcelUtil.setValidation(sheetlist, textlist, 0, 50000, 0, 0);// 第一列的前2147483647行都设置为选择列表形式.
		ExcelUtil.setPrompt(sheetlist, "test tip", "not null", 0, 50000, 1, 1);// 第二列的前2147483647行都设置提示.
		wb.write(out);
		out.close();
	}
	
	public void testExcelComment() throws IOException {
		Workbook wb = ExcelUtil.getWorkbook(ExcelType.EXCEL_2007);
		// excel文件对象
		Sheet sheet = wb.createSheet("sheetlist");// 工作表对象
		FileOutputStream out = new FileOutputStream("d:\\test.xlsx");
		ExcelUtil.setComment(sheet.createRow(0).createCell(2), "作者", "插件批注成功!插件批注成功!插件批注成功!插件批注成功!插件批注成功!插件批注成功!插件批注成功!插件批注成功!");
		wb.write(out);
		out.close();
	}
	
	public void testExcelTmpl() throws IOException {
		File file = new File(path + Info.class.getSimpleName() + ".xlsx");
		FileOutputStream out = new FileOutputStream(file);
		ExportExcel<Info> info = new ExportExcel<Info>(Info.class);
		info.exportExcelTemplate(out, ExcelType.EXCEL_2007);
	}
	
}