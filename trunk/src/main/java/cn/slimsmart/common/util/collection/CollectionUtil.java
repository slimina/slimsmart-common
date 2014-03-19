package cn.slimsmart.common.util.collection;

import java.util.Collection;
import java.util.Map;


/**
 * 集合工具类
 * 
 * @author Zhu.TW
 * 
 */
@SuppressWarnings("rawtypes")
public class CollectionUtil{

	public static boolean isEmpty(Object obj){
		if(obj==null){
			return true;
		}
		if(obj instanceof Map){
			if(((Map)obj).size()==0){
				return true;
			}
		}else if(obj instanceof Collection){
			if(((Collection)obj).size()==0){
				return true;
			}
		}
		return false;
	}
}
