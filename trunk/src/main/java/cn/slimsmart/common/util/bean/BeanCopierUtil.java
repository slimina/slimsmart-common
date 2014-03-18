package cn.slimsmart.common.util.bean;

import java.util.HashMap;
import java.util.Map;

import net.sf.cglib.beans.BeanCopier;

/**
 * 
 * @ClassName: BeanCopierUtil
 * @Description:beans对象属性复制工具
 * @author Zhu.TW
 * @date: 2014年3月14日 下午3:03:13
 */
public class BeanCopierUtil {

	private static final Map<String, BeanCopier> beanCopierMap = new HashMap<String, BeanCopier>();

	/**
	 * 
	 * beans对象属性复制
	 * @param: @param source  bean源
	 * @param: @param target  bean目标
	 */
	public static void copyProperties(Object source, Object target) {
		
		String beanKey = generateKey(source.getClass(), target.getClass());
		BeanCopier copier = null;
		if (!beanCopierMap.containsKey(beanKey)) {
			copier = BeanCopier.create(source.getClass(), target.getClass(), false);
			beanCopierMap.put(beanKey, copier);
		} else {
			copier = beanCopierMap.get(beanKey);
		}
		copier.copy(source, target, null);
	}

	private static String generateKey(Class<?> class1, Class<?> class2) {
		return class1.toString() + "-" + class2.toString();
	}
}
