package cn.slimsmart.common.document.excel;



//http://blog.csdn.net/jack0511/article/details/6179593
public class ImportExcel<T> {

	private Class<T> entityClass;

	public ImportExcel(Class<T> clazz) {
		this.entityClass = clazz;
	}
	
	
	
}
