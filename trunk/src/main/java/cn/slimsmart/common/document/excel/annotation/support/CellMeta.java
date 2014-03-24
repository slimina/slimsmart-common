package cn.slimsmart.common.document.excel.annotation.support;

import java.lang.reflect.Field;

import cn.slimsmart.common.document.excel.annotation.Cell;
import cn.slimsmart.common.entity.BaseSerializable;

public class CellMeta extends BaseSerializable{
	
	private static final long serialVersionUID = 1L;
	
	private String display;
	private Field field;
	private Cell cell;
	
	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
	public Field getField() {
		return field;
	}
	public void setField(Field field) {
		this.field = field;
	}
	public Cell getCell() {
		return cell;
	}
	public void setCell(Cell cell) {
		this.cell = cell;
	}
}
