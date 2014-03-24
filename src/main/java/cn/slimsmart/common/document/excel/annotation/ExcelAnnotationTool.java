package cn.slimsmart.common.document.excel.annotation;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import cn.slimsmart.common.document.excel.annotation.support.CellMeta;
import cn.slimsmart.common.document.excel.annotation.support.Types;
import cn.slimsmart.common.exception.AnnotationException;
import cn.slimsmart.common.util.annotation.AnnotationUtil;
import cn.slimsmart.common.util.collection.CollectionUtil;
import cn.slimsmart.common.util.reflect.ReflectionUtil;

@SuppressWarnings("rawtypes")
public class ExcelAnnotationTool {
	
	public static final String COLUMN_LENGTH="columnLength";
	
	public static Excel getTypeExcelAnnotation(Class clazz){
		Excel excelAnnotation =(Excel)AnnotationUtil.getTypeAnnotation(clazz, Excel.class);
		 if(excelAnnotation == null){
			 throw new AnnotationException("class has not Excel annotation");
		 }
		return excelAnnotation;
	}
	
	//level 目前只支持2层
	public static Map<String,Object> getFieldCellMeats(Class clazz,boolean isColLen){
		Map<String,Object> result = new HashMap<String, Object>();
		Map<String,Field> fieldMap = ReflectionUtil.getDeclaredFieldMap(clazz);
		if(CollectionUtil.isEmpty(fieldMap)){
			throw new AnnotationException("class have not fields");
		}
		CellMeta cellMeat = null;
		int colLength = 0;
		for(Entry entry: fieldMap.entrySet()){
			Cell cell = (Cell)AnnotationUtil.getDeclaredFieldAnnotation(clazz,
					entry.getKey().toString(), Cell.class);
			if(cell==null){
				continue;
			}
			colLength +=cell.length();
			cellMeat = new CellMeta();
			cellMeat.setDisplay(cell.display());;
			cellMeat.setField((Field)entry.getValue());
			cellMeat.setCell(cell);
			result.put(String.valueOf(cell.index()), cellMeat);
		}
		if(isColLen){
			result.put(COLUMN_LENGTH, colLength);
		}
		return result;
	}
	
	public static int getRowCount(Class clazz){
		Map<String,Field> fieldMap = ReflectionUtil.getDeclaredFieldMap(clazz);
		if(CollectionUtil.isEmpty(fieldMap)){
			throw new AnnotationException("class have not fields");
		}
		int t =0;
		boolean flag = false;
		for(Entry entry: fieldMap.entrySet()){
			Cell cell = (Cell)AnnotationUtil.getDeclaredFieldAnnotation(clazz,
					entry.getKey().toString(), Cell.class);
			if(cell==null){
				continue;
			}
			if(cell.type() == Types.POJO){
				flag=true;
				int n = getRowCount(((Field)entry.getValue()).getType())+1;
				if(n>t){
					t=n;
				}
			}
		}
		if(flag){
			return t;
		}else{
			return 1;
		}
	}
}
