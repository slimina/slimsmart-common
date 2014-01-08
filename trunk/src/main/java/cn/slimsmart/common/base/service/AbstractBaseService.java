package cn.slimsmart.common.base.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.slimsmart.common.base.dao.MyBatisDao;
import cn.slimsmart.common.base.model.BaseEntity;
import cn.slimsmart.common.base.model.Page;
import cn.slimsmart.common.util.ApplicationContextProvider;
import cn.slimsmart.common.util.reflect.ReflectionUtils;
import cn.slimsmart.common.util.string.StringUtil;

public class AbstractBaseService<T extends BaseEntity> implements BaseService<T> {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	protected Class<T> entityClass;

	@Autowired
	protected ApplicationContextProvider contextProvider;

	public AbstractBaseService() {
		super();
		this.entityClass = ReflectionUtils.getSuperClassGenricType(getClass());
	}

	public void delete(String id) {
		getDao().delete(id);
	}

	public T get(String id) {
		return getDao().get(id);
	}

	public int update(T entity) {
		return getDao().update(entity);
	}

	public void save(T entity) {
		getDao().insert(entity);
	}

	public BaseEntity saveEntity(BaseEntity entity) {
		return getDao().insert(entity);
	}

	public Page<T> findPage(Page<T> page, Map<String, Object> filter) {
		return getDao().findPage(page, filter);
	}

	public Page<T> findRange(Page<T> page, Map<String, Object> filter) {
		return getDao().findRange(page, filter);
	}

	public List<T> findList(Map<String, Object> filter) {
		return getDao().findList(filter);
	}

	@SuppressWarnings("unchecked")
	protected MyBatisDao<T> getDao() {
		String entityClassName = entityClass.getSimpleName();
		String daoName = StringUtil.lowercaseFirstLetter(entityClassName) + "Dao";
		logger.trace("getDao:{}", daoName);
		MyBatisDao<T> dao = (MyBatisDao<T>) ApplicationContextProvider.getBean(daoName);
		return dao;
	}

}
