package cn.slimsmart.common.document.excel.style;

import org.apache.poi.hssf.util.HSSFColor.BLACK;
import org.apache.poi.hssf.util.HSSFColor.GREY_25_PERCENT;
import org.apache.poi.hssf.util.HSSFColor.LIGHT_YELLOW;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.Workbook;

public class Styles {

	private Styles() {
	}

	public static CellStyle getTitleStyle(Workbook workbook) {
		CellStyle style = workbook.createCellStyle();
		// 设置这些样式
		style.setFillForegroundColor(GREY_25_PERCENT.index);
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setBorderRight(CellStyle.BORDER_THIN);
		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		// 生成一个字体
		Font font = workbook.createFont();
		font.setColor(BLACK.index);
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		// 把字体应用到当前的样式
		style.setFont(font);
		return style;
	}

	public static CellStyle getCellStyle(Workbook workbook) {
		CellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(LIGHT_YELLOW.index);
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setBorderRight(CellStyle.BORDER_THIN);
		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		// 自动换行 
		style.setWrapText(true);
		// 生成字体
		Font font = workbook.createFont();
		font.setBoldweight(Font.BOLDWEIGHT_NORMAL);
		// 把字体应用到当前的样式
		style.setFont(font);
		return style;
	}
	
	public static CellStyle getCellDateStyle(Workbook workbook,String format){
		 CreationHelper createHelper = workbook.getCreationHelper(); 
		 CellStyle style = getCellStyle(workbook);
		 style.setDataFormat(createHelper.createDataFormat().getFormat(format));
		 return style;
	}
	
	public static Hyperlink getCellLinkStyle(Workbook workbook,String url){
		Hyperlink link = workbook.getCreationHelper().createHyperlink(Hyperlink.LINK_URL);
	    link.setAddress(url); 
		return link;
	}
}
