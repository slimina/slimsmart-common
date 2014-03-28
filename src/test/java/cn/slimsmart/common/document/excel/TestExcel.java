package cn.slimsmart.common.document.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Before;
import org.junit.Test;

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
		u.setScore(score);
		score = new Score();
		score.setEnglish(2);
		score.setMath(2);
		u.setScore1(score);
		score = new Score();
		score.setEnglish(3);
		score.setMath(3);
		u.setScore2(score);
		list.add(u);

		User u1 = new User();
		u1.setName("小三");
		u1.setBirthday(DateUtil.addDays(new Date(), -100));
		u1.setAge(35);
		Score score1 = new Score();
		score1.setEnglish(1);
		score1.setMath(1);
		u1.setScore(score1);
		score1 = new Score();
		score1.setEnglish(2);
		score1.setMath(2);
		u1.setScore1(score1);
		score1 = new Score();
		score1.setEnglish(3);
		score1.setMath(3);
		u1.setScore2(score1);
		list.add(u1);

		User u2 = new User();
		u2.setName("小雨");
		u2.setBirthday(DateUtil.addDays(new Date(), -1000));
		u2.setAge(32);
		Score score2 = new Score();
		score2.setEnglish(1);
		score2.setMath(1);
		u2.setScore(score2);
		score2 = new Score();
		score2.setEnglish(2);
		score2.setMath(2);
		u2.setScore1(score2);
		score2 = new Score();
		score2.setEnglish(3);
		score2.setMath(3);
		u2.setScore2(score2);
		list.add(u2);

		user.exportExcel(list, out, ExcelType.EXCEL_2007);
	}

	public void testExcelSelect() throws IOException {
		Workbook wb = new HSSFWorkbook();
		;// excel文件对象
		Sheet sheetlist = wb.createSheet("sheetlist");// 工作表对象
		FileOutputStream out = new FileOutputStream("d:\\success.xls");
		String[] textlist = { "列表1", "列表2", "列表3", "列表4", "列表5" };
		sheetlist = ExcelUtil.setValidation(sheetlist, textlist, 0, 50000, 0, 0);// 第一列的前2147483647行都设置为选择列表形式.
		sheetlist = ExcelUtil.setPrompt(sheetlist, "提示信息", "不能为空", 0, 50000, 1, 1);// 第二列的前2147483647行都设置提示.
		wb.write(out);
		out.close();
	}
}