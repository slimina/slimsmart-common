package cn.slimsmart.common.jsqlparser.statement.create.table;

import java.util.List;

import cn.slimsmart.common.jsqlparser.statement.select.PlainSelect;
@SuppressWarnings("rawtypes")
public class ColDataType {

    private String dataType;
    
	private List argumentsStringList;

    public List getArgumentsStringList() {
        return argumentsStringList;
    }

    public String getDataType() {
        return dataType;
    }

    public void setArgumentsStringList(List list) {
        argumentsStringList = list;
    }

    public void setDataType(String string) {
        dataType = string;
    }

    public String toString() {
        return dataType + (argumentsStringList!=null?" "+PlainSelect.getStringList(argumentsStringList, true, true):"");
    }
}