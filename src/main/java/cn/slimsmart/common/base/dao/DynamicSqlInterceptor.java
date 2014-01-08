package cn.slimsmart.common.base.dao;

import java.sql.Connection;
import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.slimsmart.common.jsqlparser.SqlParserHelper;
import cn.slimsmart.common.jsqlparser.dialect.Dialect;
import cn.slimsmart.common.jsqlparser.dialect.MySQLDialect;
import cn.slimsmart.common.jsqlparser.dialect.OracleDialect;

/**
 * mybatis拦截器
 * @author Zhu.TW
 *
 */
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class DynamicSqlInterceptor implements Interceptor {
	
	private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();  
    private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();  

	protected static Logger logger = LoggerFactory.getLogger(DynamicSqlInterceptor.class);
	SqlParserHelper sqlParser = SqlParserHelper.getInstance();

	public Object intercept(Invocation invocation) throws Throwable {
		logger.trace(" ==> Interceptor begin ......");
		long start = 0L;
		if (logger.isTraceEnabled()) {
			start = System.currentTimeMillis();
		}
		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
		MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
		String boundSql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");
		logger.trace("original sql : \n {}", boundSql);
		DefaultParameterHandler defaultParameterHandler = (DefaultParameterHandler) metaStatementHandler
				.getValue("delegate.parameterHandler");
		Object paramObj = defaultParameterHandler.getParameterObject();
		logger.trace("sql parameters: {}", paramObj == null ? null : paramObj.toString());
		Configuration configuration = (Configuration) metaStatementHandler.getValue("delegate.configuration");
		
		// 处理物理分页
		RowBounds rowBounds = (RowBounds) metaStatementHandler.getValue("delegate.rowBounds");
		if (rowBounds == null || rowBounds == RowBounds.DEFAULT) {
			logger.trace("rowBounds is null, no need to wrap the pagination sql.");
		} else {
			Dialect.Type databaseType = null;
			try {
				databaseType = Dialect.Type.valueOf(configuration.getVariables().getProperty("dialect").toUpperCase());
			} catch (Exception e) {
				// ignore
			}
			if (databaseType == null) {
				throw new RuntimeException("the value of the dialect property in configuration.xml is not defined : "
						+ configuration.getVariables().getProperty("dialect"));
			}
			Dialect dialect = null;
			switch (databaseType) {
			case ORACLE:
				dialect = new OracleDialect();
				break;
			case MYSQL:// 需要实现MySQL的分页逻辑
				dialect = new MySQLDialect();
				break;
			}
			boundSql = dialect.getLimitString(boundSql, rowBounds.getOffset(), rowBounds.getLimit());
			metaStatementHandler.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);
			metaStatementHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);
		}
		metaStatementHandler.setValue("delegate.boundSql.sql", boundSql);
		logger.trace("wrapped sql : \n {} ", boundSql);
		if (logger.isTraceEnabled()) {
			long end = System.currentTimeMillis();
			logger.debug(" ==> Dynamic Sql Interceptor execution time : {}ms", (end - start));
		}
		return invocation.proceed();
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties arg0) {
		// TODO Auto-generated method stub
	}

}