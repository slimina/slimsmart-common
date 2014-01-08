package cn.slimsmart.common.base.service;

import java.util.List;
import java.util.Map;

import cn.slimsmart.common.base.model.BaseEntity;
import cn.slimsmart.common.base.model.Page;

public interface BaseService<T extends BaseEntity> {

	/**
	 * 根据Id删除实体
	 * 
	 * @param id
	 */
	void delete(String id);

	/**
	 * 根据Id获取实体
	 * 
	 * @param id
	 */
	T get(String id);

	/**
	 * 更新实体
	 *
     */
	int update(T entity);

	/**
	 * 保存实体
	 *
     */
	void save(T entity);

	/**
	 * 查询实体列表分页
	 * 
	 */
	Page<T> findPage(Page<T> page, Map<String, Object> filter);
	
	/**
	 * 
	 * 根据条件查询实体集合
	 * @param filter
	 * @return
	 */
	List<T> findList(Map<String, Object> filter);

	/**
	 * 查询从第N条到第N条之间的所有数据,一般用于导出Excel
	 * 
	 * @param page
	 * @param filter
	 * @return
	 */
	Page<T> findRange(Page<T> page, Map<String, Object> filter);

    /**
     * 保存并返回实体
     *
     * @param entity      实体
     * @return 实体
     */
	BaseEntity saveEntity(BaseEntity entity);
}
