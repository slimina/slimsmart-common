package cn.slimsmart.common.base.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页实体
 * @author asus
 *
 * @param <T>
 */
@SuppressWarnings("serial")
public class Page<T> implements Serializable {
	//-- 公共变量 --//
	public static final String ASC = "asc";
	public static final String DESC = "desc";

	// default page size
	public static final int SIZE = 20;
	
	//-- 分页参数 --//
	protected int page = 1;
	
	protected int limit = SIZE;
	protected long totalCount = 0;
	
	
	//--从1开始计数为第一条--//
	protected int startIndex;
	protected int endIndex;

	//-- 返回结果 --//
	protected List<T> items = new ArrayList<T>();

	//-- 构造函数 --//
	public Page() {
	}

	public Page(int limit) {
		this.limit = limit;
	}

	//-- 访问查询参数函数 --//
	/**
	 * 获得当前页的页号,序号从1开始,默认为1.
	 */
	public int getPage() {
		return page;
	}

	/**
	 * 设置当前页的页号,序号从1开始,低于1时自动调整为1.
	 */
	public void setPage(final int page) {
		this.page = page;

		if (page < 1) {
			this.page = 1;
		}
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
		if(limit<1){
			this.limit=1;
		}
	}

	/**
	 * 根据pageNo和pageSize计算当前页第一条记录在总结果集中的位置,序号从1开始.
	 */
	public int getFirst() {
		return ((page - 1) * limit) + 1;
	}

	//-- 访问查询结果函数 --//


	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * 根据pageSize与totalCount计算总页数, 默认值为-1.
	 */
	public long getTotalPages() {
		if (totalCount < 0) {
			return -1;
		}

		long count = totalCount / limit;
		if (totalCount % limit > 0) {
			count++;
		}
		return count;
	}

	/**
	 * 是否还有下一页.
	 */
	public boolean isHasNext() {
		return (page + 1 <= getTotalPages());
	}

	/**
	 * 取得下页的页号, 序号从1开始.
	 * 当前页为尾页时仍返回尾页序号.
	 */
	public int getNextPage() {
		if (isHasNext()) {
			return page + 1;
		} else {
			return page;
		}
	}

	/**
	 * 是否还有上一页.
	 */
	public boolean isHasPre() {
		return (page - 1 >= 1);
	}

	/**
	 * 取得上页的页号, 序号从1开始.
	 * 当前页为首页时返回首页序号.
	 */
	public int getPrePage() {
		if (isHasPre()) {
			return page - 1;
		} else {
			return page;
		}
	}

	
	public int getStartIndex() {
		return startIndex;
	}

	
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	
	public int getEndIndex() {
		return endIndex;
	}

	
	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Page");
        sb.append("{page=").append(page);
        sb.append(", rp=").append(limit);
        sb.append(", total=").append(totalCount);
        sb.append(", items=").append(items);
        sb.append('}');
        return sb.toString();
    }
}