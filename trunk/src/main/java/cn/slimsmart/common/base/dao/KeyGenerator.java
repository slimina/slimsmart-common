package cn.slimsmart.common.base.dao;

import java.util.Date;
import java.util.Map;

import cn.slimsmart.common.base.model.BaseEntity;
import cn.slimsmart.common.base.model.Page;

@SuppressWarnings("rawtypes")
public class KeyGenerator {
	public static String getKey(String hql, Object... values) {
		StringBuffer sb = new StringBuffer();
		sb.append(hql);
		if (values != null && values.length > 0) {
			for (Object v : values) {
				if (v instanceof Date) {
					sb.append(":"+((Date)v).getTime());
				} else {
					sb.append(":"+v.toString());
				}
			}		
		}
		return sb.toString();
	}

	public static String getKey(Page page, String hql, Object... values) {
		StringBuffer sb = new StringBuffer();
		sb.append("P"+page.getLimit()).append("."+page.getPage()).append(":").append(hql);
		if (values != null && values.length > 0) {
			for (Object v : values) {
				if (v instanceof Date) {
					sb.append(":"+((Date)v).getTime());
				} else if (v == null){
					sb.append(":");
				} else {
					sb.append(":"+v.toString());
				}
			}		
		}
		return sb.toString();
	}

	public static String getKey(final String hql, Map<String, ?> values) {
		StringBuffer sb = new StringBuffer();
		sb.append(hql);
		if (values != null && values.size() > 0) {
			for (String key : values.keySet()) {
				sb.append(":").append(key);
				Object v = values.get(key);
				if (v instanceof Date) {
					sb.append("="+((Date)v).getTime());
				} else {
					sb.append("="+v.toString());
				}
			}
		}
		return sb.toString();
	}
	
	public static String getKey(Page page, final String hql, Map<String, ?> values) {
		StringBuffer sb = new StringBuffer();
		sb.append("P"+page.getLimit()).append("."+page.getPage()).append(":").append(hql);
		if (values != null && values.size() > 0) {
			for (String key : values.keySet()) {				
				Object v = values.get(key);
				if(v != null){
					sb.append(":").append(key);
					if (v instanceof Date) {
						sb.append("="+((Date)v).getTime());
					} else {
						sb.append("="+v.toString());
					}
				}				
			}
		}
		return sb.toString();
	}

	public static String getKey(Class entityClass, String propertyName, Object value) {
		StringBuffer sb = new StringBuffer();
		sb.append(getEntityClassName(entityClass)).append(":");
		sb.append(propertyName).append(":");
		if (value instanceof Date) {
			sb.append(""+((Date)value).getTime());
		} else {
			sb.append(value.toString());
		}
		return sb.toString();
	}
	
	public static String getKey(BaseEntity obj) {
		return getKey(obj.getClass(), obj.getId());
	}
	
	public static String getKey(Class obj ,Object... pk){
		StringBuffer sb = new StringBuffer();
		sb.append(getEntityClassName(obj.getClass())).append(":");
		if (pk != null && pk.length > 0) {
			for (Object v : pk) {
				if (v == null){
					sb.append(":");
				} else {
					sb.append(":"+v.toString());
				}
			}		
		}
		return sb.toString();
	}
	
	
	public static String getKey(Class entityClass, Long id) {
		StringBuffer sb = new StringBuffer();
		sb.append(getEntityClassName(entityClass)).append("@"+id);
		return sb.toString();
	}
	
	private static String getEntityClassName(Class entityClass) {
		String clsName = entityClass.getName();
		if (clsName.contains("$$")) {
			clsName = entityClass.getSuperclass().getName();
		}
		return clsName;
	}
}
