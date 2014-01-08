package cn.slimsmart.common.jsqlparser.expression;

import cn.slimsmart.common.jsqlparser.statement.select.SubSelect;

public class AnyComparisonExpression implements Expression {
	private SubSelect subSelect;
	
	public AnyComparisonExpression(SubSelect subSelect) {
		this.subSelect = subSelect;
	}
	
	public SubSelect GetSubSelect() {
		return subSelect;
	}

	public void accept(ExpressionVisitor expressionVisitor) {
		expressionVisitor.visit(this);
	}
}
