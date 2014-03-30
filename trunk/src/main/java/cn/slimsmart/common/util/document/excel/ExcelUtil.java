package cn.slimsmart.common.util.document.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cn.slimsmart.common.document.excel.support.ExcelType;

@SuppressWarnings("unchecked")
public class ExcelUtil {
	
	private ExcelUtil(){}
	
	/**
	 * 取单元格的数据
	 * @param cell
	 * @return
	 */
	public <X> X getCellStringValue(Cell cell) {      
        X cellValue = null;      
        switch (cell.getCellType()) {      
        case Cell.CELL_TYPE_STRING://字符串类型  
        	cellValue = (X)cell.getStringCellValue();     
        	 break;
        case Cell.CELL_TYPE_NUMERIC: //数值类型  
        	cellValue = (X)Double.valueOf(cell.getNumericCellValue());      
            break;      
        case Cell.CELL_TYPE_FORMULA: //公式  
            cell.setCellType(Cell.CELL_TYPE_NUMERIC);      
            cellValue = (X)Double.valueOf(cell.getNumericCellValue());     
            break;      
        case Cell.CELL_TYPE_BLANK:  //空白    
            cellValue=(X)"";      
            break;      
        case Cell.CELL_TYPE_BOOLEAN: //boolean
        	cellValue=(X)Boolean.valueOf(cell.getBooleanCellValue());
            break;      
        case Cell.CELL_TYPE_ERROR: //错误信息
        	cellValue = (X)Byte.valueOf(cell.getErrorCellValue());
            break;      
        default:      
            break;      
        }      
        return cellValue;      
    }     
	
	
	/** 
     * 设置单元格上提示 
     * @param sheet  要设置的sheet. 
     * @param promptTitle 标题 
     * @param promptContent 内容 
     * @param firstRow 开始行 
     * @param endRow  结束行 
     * @param firstCol  开始列 
     * @param endCol  结束列 
     * @return 设置好的sheet. 
     */ 
	public static void setPrompt(final Sheet sheet, String promptTitle,  
            String promptContent, int firstRow, int endRow ,int firstCol,int endCol) {  
        // 构造constraint对象  
		DataValidationConstraint constraint = sheet.getDataValidationHelper().createCustomConstraint("IV1");  
        // 四个参数分别是：起始行、终止行、起始列、终止列  
        CellRangeAddressList regions = new CellRangeAddressList(firstRow,endRow,firstCol, endCol);  
        // 数据有效性对象  
        DataValidation data_validation_view = sheet.getDataValidationHelper().createValidation(constraint,regions);
        data_validation_view.createPromptBox(promptTitle, promptContent);  
        sheet.addValidationData(data_validation_view);  
    } 
	
	public static void setComment(final Cell cell,String title,String message){
		Sheet sheet = cell.getSheet();
		Drawing drawing= sheet.createDrawingPatriarch(); 
		ClientAnchor  anchor = sheet.getWorkbook().getCreationHelper().createClientAnchor();
		Comment comment = drawing.createCellComment(anchor);
		comment.setAuthor(title);
		comment.setString(sheet.getWorkbook().getCreationHelper().createRichTextString(message));
		cell.setCellComment(comment);
	}
	
	/** 
     * 设置某些列的值只能输入预制的数据,显示下拉框. 
     * @param sheet 要设置的sheet. 
     * @param textlist 下拉框显示的内容 
     * @param firstRow 开始行 
     * @param endRow 结束行 
     * @param firstCol   开始列 
     * @param endCol  结束列 
     * @return 设置好的sheet. 
     */  
    public static void setValidation(final Sheet sheet,  
            String[] textlist,int firstRow, int endRow, int firstCol,  
            int endCol) {  
        // 加载下拉列表内容  
    	DataValidationConstraint constraint = sheet.getDataValidationHelper().createExplicitListConstraint(textlist);  
        // 设置数据有效性加载在哪个单元格上,四个参数分别是：起始行、终止行、起始列、终止列  
        CellRangeAddressList regions = new CellRangeAddressList(firstRow,endRow, firstCol, endCol);  
        // 数据有效性对象  
        DataValidation data_validation_list = sheet.getDataValidationHelper().createValidation(constraint,regions);
        sheet.addValidationData(data_validation_list);  
    } 
    
    public static Workbook getWorkbook(int excelType){
    	Workbook workbook = null;
    	switch (excelType) {
		case ExcelType.EXCEL_2003:
			workbook = new HSSFWorkbook();
			break;
		case ExcelType.EXCEL_2007:
			workbook = new XSSFWorkbook();
			break;
		default:
			break;
		}
    	return workbook;
    }
    
}
