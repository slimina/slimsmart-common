package cn.slimsmart.common.jsqlparser.statement.truncate;

import cn.slimsmart.common.jsqlparser.schema.Table;
import cn.slimsmart.common.jsqlparser.statement.Statement;
import cn.slimsmart.common.jsqlparser.statement.StatementVisitor;

/**
 * A TRUNCATE TABLE statement
 */
public class Truncate implements Statement {
	private Table table;

	public void accept(StatementVisitor statementVisitor) {
		statementVisitor.visit(this);
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public String toString() {
		return "TRUNCATE TABLE "+table;
	}
}
