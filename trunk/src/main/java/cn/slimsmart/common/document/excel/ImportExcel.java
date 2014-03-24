package cn.slimsmart.common.document.excel;

public class ImportExcel<T> {

	private Class<T> entityClass;

	public ImportExcel(Class<T> clazz) {
		this.entityClass = clazz;
	}
	
	
	
}
