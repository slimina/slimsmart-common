package cn.slimsmart.common.jsqlparser;

import java.io.StringReader;

import cn.slimsmart.common.jsqlparser.parser.CCJSqlParserManager;
import cn.slimsmart.common.jsqlparser.statement.Statement;

public class SqlParserHelper {
	CCJSqlParserManager sqlParser;
	public Statement parseSql(String sql) throws JSQLParserException {
		Statement statement = null;
		statement = sqlParser.parse(new StringReader(sql));
		return statement;
	}

	private SqlParserHelper() {
		super();
		sqlParser = new CCJSqlParserManager();
	}

	private static SqlParserHelper sqlWrapper = new SqlParserHelper();

	public static SqlParserHelper getInstance() {
		return sqlWrapper;
	}
}
