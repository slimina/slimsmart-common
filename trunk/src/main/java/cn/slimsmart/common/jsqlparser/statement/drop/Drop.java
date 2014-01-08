package cn.slimsmart.common.jsqlparser.statement.drop;

import java.util.List;

import cn.slimsmart.common.jsqlparser.statement.Statement;
import cn.slimsmart.common.jsqlparser.statement.StatementVisitor;
import cn.slimsmart.common.jsqlparser.statement.select.PlainSelect;

@SuppressWarnings("rawtypes")
public class Drop implements Statement {
	private String type;
	private String name;
	private List parameters;
	
	public void accept(StatementVisitor statementVisitor) {
		statementVisitor.visit(this);
	}

	public String getName() {
		return name;
	}

	public List getParameters() {
		return parameters;
	}

	public String getType() {
		return type;
	}

	public void setName(String string) {
		name = string;
	}

	public void setParameters(List list) {
		parameters = list;
	}

	public void setType(String string) {
		type = string;
	}

	public String toString() {
		String sql = "DROP "+type+" "+name;
		
		if( parameters != null && parameters.size() > 0) {
			sql += " "+PlainSelect.getStringList(parameters);
		}
		
		return sql;
	}
}
