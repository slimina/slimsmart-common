package cn.slimsmart.common.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import cn.slimsmart.common.util.collection.CollectionUtil;

/**
 * 分页参数对象
 * 
 * @author Zhu.TW
 * 
 */
public class PageParam extends BaseSerializable {

	private static final long serialVersionUID = 1L;

	private int page = 1;
	private int limit = 10;

	private Map<String, Object> filters = null;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public void setFilter(String key, String vlaue) {
		if (CollectionUtil.isEmpty(filters)) {
			filters = new HashMap<String, Object>();
		}
		filters.put(key, vlaue);
	}

	public void clearFilters() {
		if (CollectionUtil.isEmpty(filters)) {
			filters = new HashMap<String, Object>();
		}
		filters.clear();
	}

	public Map<String, Object> getFilters() {
		if (CollectionUtil.isEmpty(filters)) {
			filters = new HashMap<String, Object>();
		}
		filters.put("startIndex", getStartIndex());
		filters.put("endIndex", getEndIndex());
		return filters;
	}

	public void setFilters(Map<String, Object> filters) {
		if (CollectionUtil.isEmpty(filters)) {
			return;
		}
		if (this.filters != null) {
			for (Entry<String, Object> entry : filters.entrySet()) {
				filters.put(entry.getKey(), entry.getValue());
			}
		} else {
			this.filters = filters;
		}
	}

	public int getStartIndex() {
		return (page - 1) * limit;
	}

	public int getEndIndex() {
		return getStartIndex() + limit;
	}
}
