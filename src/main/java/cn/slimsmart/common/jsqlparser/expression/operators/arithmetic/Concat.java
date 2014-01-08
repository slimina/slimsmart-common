package cn.slimsmart.common.jsqlparser.expression.operators.arithmetic;

import cn.slimsmart.common.jsqlparser.expression.BinaryExpression;
import cn.slimsmart.common.jsqlparser.expression.ExpressionVisitor;

public class Concat extends BinaryExpression {

	public void accept(ExpressionVisitor expressionVisitor) {
		expressionVisitor.visit(this);
	}
	
	public String getStringExpression() {
		return "||";
	}

}
