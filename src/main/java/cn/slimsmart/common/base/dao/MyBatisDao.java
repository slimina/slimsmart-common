package cn.slimsmart.common.base.dao;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import cn.slimsmart.common.base.model.BaseEntity;
import cn.slimsmart.common.base.model.Page;
import cn.slimsmart.common.util.UUID;
import cn.slimsmart.common.util.reflect.ReflectionUtils;
import cn.slimsmart.common.util.string.StringUtil;


/**
 * MyBatis基类
 * 
 * @author Zhu.TW
 * 
 * @param <T>
 */
@SuppressWarnings("unchecked")
public class MyBatisDao<T extends BaseEntity> extends SqlSessionDaoSupport {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected Class<T> entityClass;

	/**
	 * 用于Dao层子类使用的构造函数. 通过子类的泛型定义取得对象类型Class. eg. public class UserDao extends
	 * SimpleHibernateDao<User>
	 */
	public MyBatisDao() {
		this.entityClass = ReflectionUtils.getSuperClassGenricType(getClass());
	}

	/**
	 * 用于用于省略Dao层, 在Service层直接使用通用MyBatisDao的构造函数. 在构造函数中定义对象类型Class. eg.
	 * SimpleHibernateDao<User> userDao = new MyBatisDao<User>(sessionFactory,
	 * User.class);
	 */
	public MyBatisDao(final Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	/**
	 * 通用插入方法 statementName = findPage
	 * 
	 * @param ctfoContext
	 * @param Entity
	 */
	public BaseEntity insert(final BaseEntity entity) {
		return insert("insert", entity);
	}

	/**
	 * 批量插入实体
	 * 
	 * @param ctfoContext
	 * @param Entitys
	 * @return
	 */
	public <X> List<X> batchInsert(final List<X> Entitys) {
		return batchInsert("batchInsert", Entitys);
	}

	/**
	 * 批量删除
	 * 
	 * @param ctfoContext
	 * @param Entitys
	 */
	public <X> void batchDelete(final List<X> Entitys) {
		batchDelete("batchDelete", Entitys);
	}

	/**
	 * 单表查询方法，返回对象实体（包含全部属性的值），要求mapper实现语句时select出所有的字段 statementName = get
	 * 
	 * @param id
	 *            主键
	 * @return
	 */
	public T get(final String id) {
		return get("get", id);
	}

	/**
	 * 根据ID删除单表数据 statementName = delete
	 * 
	 * @param id
	 */
	public void delete(final String id) {
		delete("delete", id);
	}

	/**
	 * 保存修改的对象. 返回0表示更新失败。如果这个值已经被人修改过，会抛出ConcurrentModificationException
	 * statementName = update
	 */
	public int update(final T Entity) throws ConcurrentModificationException {
		return update("update", Entity);
	}

	/**
	 * 根据查询条件取记录数，需要mapper提供count实现，findPage实现 statementName = count
	 * 
	 * @return
	 */
	private long count(final Map<String, Object> filter) {
		return count("count", filter);
	}

	/**
	 * 公共分页方法，需要mapper提供count实现，findPage实现 statementName = findPage
	 * 
	 * @param ctfoContext
	 * @param page
	 * @param filter
	 * @return
	 */
	public Page<T> findPage(final Page<T> page, final Map<String, Object> filter) {
		long totalCount = count(filter);
		page.setTotalCount(totalCount);
		return findPage(page, "findPage", filter);
	}

	/**
	 * 根据条件查询实体集合 statementName = findList
	 * 
	 * @param ctfoContex
	 * @param filter
	 * @return
	 */
	public List<T> findList(final Map<String, Object> filter) {
		return findList("findList", filter);
	}

	/**
	 * 公共分段查询方法，查询从第几条到第几条数据的记录 statementName = findRange
	 * 
	 * @param page
	 * @param filter
	 * @return
	 */
	public Page<T> findRange(final Page<T> page,
			final Map<String, Object> filter) {
		return findRange(page, "findRange", filter);
	}

	private Map<?, ?> toParameterMap(Object parameter) {
		if (parameter == null) {
			return new HashMap<Object, Object>();
		}
		if (parameter instanceof Map) {
			return (Map<?, ?>) parameter;
		} else {
			try {
				return PropertyUtils.describe(parameter);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				return null;
			}
		}
	}

	/**
	 * 添加实体
	 * 
	 * @param ctfoContext
	 * @param statementName
	 * @param entity
	 */
	protected BaseEntity insert(String statementName, final BaseEntity entity) {

		Assert.notNull(statementName, "statementName不能为空");
		Assert.notNull(entity, "entity不能为空");

		// 这个if不可以去掉，有些需要调用方，自己处理，不一定是取ctfoContext
		if (StringUtil.isEmpty(entity.getId())) {
			entity.setId(UUID.getUUID());
		}

		getSqlSession().insert(getStatementName(statementName), entity);
		return entity;
	}

	/**
	 * 批量插入实体
	 * 
	 * @param ctfoContext
	 * @param statementName
	 * @param Entitys
	 * @return
	 */
	protected <X> List<X> batchInsert(String statementName,
			final List<X> Entitys) {

		Assert.notNull(statementName, "statementName不能为空");
		Assert.notNull(Entitys, "entity不能为空");

		getSqlSession().getConfiguration().setDefaultExecutorType(
				ExecutorType.BATCH);
		for (X x : Entitys) {
			getSqlSession().insert(getStatementName(statementName), x);
		}
		return Entitys;
	}

	/**
	 * 批量删除
	 * 
	 * @param ctfoContext
	 * @param statementName
	 * @param Entitys
	 */
	public <X> void batchDelete(String statementName, final List<X> Entitys) {

		Assert.notNull(statementName, "statementName不能为空");
		Assert.notNull(Entitys, "Entitys不能为空");

		getSqlSession().getConfiguration().setDefaultExecutorType(
				ExecutorType.BATCH);
		for (X x : Entitys) {
			getSqlSession().delete(getStatementName(statementName), x);
		}
	}

	/**
	 * 根据Id删除实体
	 * 
	 * @param ctfoContext
	 * @param statementName
	 */
	protected void delete(String statementName, final Object obj) {

		Assert.notNull(statementName, "statementName不能为空");
		Assert.notNull(obj, "obj不能为空");

		getSqlSession().delete(getStatementName(statementName), obj);
	}

	/**
	 * 更新实体
	 * 
	 * @param ctfoContext
	 * @param statementName
	 * @param obj
	 *            可以为string int long Entity Map eg.
	 * @return
	 * @throws ConcurrentModificationException
	 */
	protected int update(String statementName, final Object obj)
			throws ConcurrentModificationException {

		Assert.notNull(statementName, "statementName不能为空");
		Assert.notNull(obj, "obj不能为空");

		if (obj instanceof BaseEntity) {
			BaseEntity entity = (BaseEntity) obj;
			entity.setUpdateDate(new Date());
		}

		int count = getSqlSession()
				.update(getStatementName(statementName), obj);
		logger.info("updated: {}", count);
		return count;
	}

	/**
	 * 根据Id获取实体对象
	 * 
	 * @param ctfoContext
	 * @param statementName
	 * @return
	 */
	protected <X> X get(String statementName, final Object obj) {
		Assert.notNull(obj, "obj不能为空");

		return (X) getSqlSession().selectOne(getStatementName(statementName),
				obj);
	}

	/**
	 * 根据条件查询实体集合
	 * 
	 * @param ctfoContext
	 * @param statementName
	 * @param obj
	 *            可以为string int long Entity Map eg.
	 * @return
	 */
	public <X> List<X> findList(String statementName, Object obj) {

		Assert.notNull(statementName, "statementName不能为空");

		List<X> result = getSqlSession().selectList(
				getStatementName(statementName), obj);
		return result;
	}

	/**
	 * 根据条件获取查询总数目
	 * 
	 * @param ctfoContext
	 * @param statementName
	 * @param obj
	 *            可以为string int long Entity map eg.
	 * @return
	 */
	protected long count(String statementName, Object obj) {

		Assert.notNull(statementName, "statementName不能为空");
		long result = ((Number) getSqlSession().selectOne(
				getStatementName(statementName), obj)).longValue();
		return result;
	}

	/**
	 * 返回任意类型的单个对象
	 * 
	 * @param ctfoContext
	 * @param statementName
	 * @param obj
	 * @return
	 */
	protected Object selectOne(String statementName, final Object obj) {
		Assert.notNull(statementName, "statementName不能为空");

		Object result = getSqlSession().selectOne(
				getStatementName(statementName), obj);
		return result;
	}

	/**
	 * 公共分页
	 * 
	 * @param ctfoContext
	 * @param page
	 * @param statementName
	 * @param filter
	 * @return
	 */
	protected <X> Page<X> findPage(final Page<X> page, String statementName,
			Map<String, Object> filter) {

		Assert.notNull(page, "page不能为空");
		Assert.notNull(statementName, "statementName不能为空");

		String key = KeyGenerator.getKey(page, getStatementName(statementName),
				filter);
		Map<String, Integer> parameter = toParameterMap(filter, page);
		RowBounds rowBounds = getRowBounds(page);
		List<X> result = getSqlSession().selectList(
				getStatementName(statementName), parameter, rowBounds);
		postProcess(page, result, key);
		return page;
	}

	protected <X> Page<X> findPage(final Page<X> page, String statementName,
			Object filter) {

		Assert.notNull(page, "page不能为空");
		Assert.notNull(statementName, "statementName不能为空");

		RowBounds rowBounds = getRowBounds(page);
		List<X> result = getSqlSession().selectList(
				getStatementName(statementName), filter, rowBounds);
		page.setItems(result);
		return page;
	}

	/**
	 * 查询一段数据
	 * 
	 * @param ctfoContext
	 * @param page
	 * @param statementName
	 * @param filter
	 * @return
	 */
	protected <X> Page<X> findRange(final Page<X> page, String statementName,
			Map<String, Object> filter) {

		Assert.notNull(page, "page不能为空");
		Assert.notNull(statementName, "statementName不能为空");

		String key = KeyGenerator.getKey(page, getStatementName(statementName),
				filter);
		List<X> result = getSqlSession().selectList(
				getStatementName(statementName), toParameterMap(filter, page),
				getRangeRowBounds(page));
		postProcess(page, result, key);
		return page;
	}

	private <X> void postProcess(final Page<X> page, List<X> result, String key) {
		if (result == null) {
			result = new ArrayList<X>();
		}
		page.setItems(result);
	}

	/*
	 * startRow,endRow : 用于oracle分页使用,从1开始 offset,limit : 用于mysql 分页使用,从0开始
	 */
	private Map<String, Integer> toParameterMap(Object parameter, Page<?> p) {
		Map<String, Integer> map = (Map<String, Integer>) toParameterMap(parameter);
		map.put("startRow", p.getFirst());
		map.put("endRow", p.getFirst() + p.getLimit() - 1);
		map.put("offset", p.getFirst() - 1);
		map.put("limit", p.getLimit());
		return map;
	}

	private RowBounds getRowBounds(Page<?> p) {
		return new RowBounds(p.getFirst() - 1, p.getLimit());
	}

	private RowBounds getRangeRowBounds(Page<?> p) {
		return new RowBounds(p.getStartIndex() - 1, p.getEndIndex()
				- p.getStartIndex() + 1);
	}

	private String getStatementName(String statementName) {
		return entityClass.getName() + "." + statementName;
	}
}