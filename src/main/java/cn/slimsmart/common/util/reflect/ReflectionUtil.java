package cn.slimsmart.common.util.reflect;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.Validate;

/**
 * 反射工具类
 * 
 * @author Zhu.TW
 * 
 */
@SuppressWarnings("rawtypes")
public class ReflectionUtil {
	
	private ReflectionUtil(){}

	private static final Map<Class, Map<String, Field>> fieldsMap = new HashMap<Class, Map<String, Field>>();
	
	/**
     * Class的名称.
     * 
     * @param className
     *            名称
     * @return 如果没有找到 null
     */
    public static Class<?> classForName(final String className) {
        try {
            return Class.forName(className);
        } catch (final ClassNotFoundException e) {
        	throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 取得构造.
     * 
     * @param cls
     *            Class
     * @param args
     *            参数
     * @return 没有则返回 null
     */
    public static Constructor<?> getConstructor(final Class<?> cls, final Class<?>... args) {
        try {
            return cls.getDeclaredConstructor(args);
        } catch (final Exception e) {
        	throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 新实例.
     * 
     * @param <T>
     *            <T>
     * @param cls
     *            Class
     * @return 新实例
     */
    @SuppressWarnings("unchecked")
    public static <T> T newInstance(final Class<?> cls) {
        final Constructor<?> con = getConstructor(cls, new Class<?>[0]);
        return (T) newInstance(con, new Object[0]);
    }

    /**
     * 新实例.
     * 
     * @param <T>
     *            <T>
     * @param con
     *            构造
     * @param args
     *            参数
     * @return 如果没有则返回 null
     */
    @SuppressWarnings("unchecked")
    public static <T> T newInstance(final Constructor<?> con, final Object... args) {
        try {
            con.setAccessible(true);
            return (T) con.newInstance(args);
        } catch (final Exception e) {
        	throw new RuntimeException(e.getMessage(), e);
        }
    }
    
    /**
     * 新实例.
     * 
     * @param <T>
     *            <T>
     * @param className
     *            名称
     * @return 新实例
     */
    public static <T> T newInstance(final String className) {
        final Class<?> cls = ReflectionUtil.classForName(className);
        return ReflectionUtil.<T> newInstance(cls);
    }

    /**
     * 新实例.
     * 
     * @param <T>
     *            <T>
     * @param <T2>
     *            <T2>
     * @param className
     *            名称
     * @param arg
     *            参数
     * @param argValue
     *            参数值
     * @return 新实例
     */
    public static <T, T2> T newInstance(final String className, final Class<T2> arg, final T2 argValue) {
        final Class<?> cls = ReflectionUtil.classForName(className);
        final Constructor<?> con = ReflectionUtil.getConstructor(cls, arg);
        return ReflectionUtil.<T> newInstance(con, argValue);
    }

	public static void putField(Class clazz, String fieldName, Field field) {
		Validate.notNull(clazz, "clazz can't be null");
		Validate.notNull(fieldName, "fieldName can't be null");
		Validate.notNull(field, "field can't be null");

		Map<String, Field> fields = fieldsMap.get(clazz);
		if (fields == null) {
			fields = new HashMap<String, Field>();
			fieldsMap.put(clazz, fields);
		}
		fields.put(fieldName, field);
	}

	public static Field getField(Class clazz, String fieldName) {
		Map<String, Field> fields = fieldsMap.get(clazz);
		if (fields == null) {
			return null;
		}
		return fields.get(fieldName);
	}

	/**
	 * 直接读取对象属性值,无视private/protected修饰符,不经过getter函数.
	 */
	public static Object getFieldValue(final Object object, final String fieldName) {
		Validate.notNull(object, "object can't be null");
		Validate.notNull(fieldName, "fieldName can't be null");
		Field field = getDeclaredField(object, fieldName);

		if (field == null) {
			throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");
		}

		Object result = null;
		try {
			result = field.get(object);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 直接设置对象属性值,无视private/protected修饰符,不经过setter函数.
	 */
	public static void setFieldValue(final Object object, final String fieldName, final Object value) {
		Validate.notNull(object, "object can't be null");
		Validate.notNull(fieldName, "fieldName can't be null");
		Field field = getDeclaredField(object, fieldName);
		if (field == null)
			throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");

		try {
			field.set(object, value);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 循环向上转型,获取类的DeclaredField.
	 */
	public static Field getDeclaredField(final Object object, final String fieldName) {
		Validate.notNull(object, "object can't be null");
		Validate.notNull(fieldName, "fieldName can't be null");
		Class clazz = object.getClass();
		if (getField(clazz, fieldName) != null) {
			return getField(clazz, fieldName);
		}

		for (Class superClass = clazz; superClass != Object.class; superClass = superClass.getSuperclass()) {
			try {
				Field field = superClass.getDeclaredField(fieldName);
				field.setAccessible(true);
				putField(clazz, fieldName, field);
				return field;
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 循环向上转型,获取类的DeclaredField.
	 */
	public static Map<String, Field> getDeclaredFieldMap(Object valueBean) {
		Class clazz = valueBean.getClass();
		Map<String, Field> fieldMap = new HashMap<String, Field>();
		Field[] fields = clazz.getDeclaredFields();
		Method[] methods = clazz.getDeclaredMethods();
		for (Field field : fields) {
			String filedName = field.getName();
			String methodName = getMethodName(filedName);
			for (Method method : methods) {
				if (methodName.equals(method.getName())) {
					field.setAccessible(true);
					fieldMap.put(filedName, field);
				}
			}
		}
		return fieldMap;
	}

	private static String getMethodName(String fieldName) {
		return new StringBuilder("get").append(fieldName.substring(0, 1).toUpperCase()).append(fieldName.substring(1)).toString();
	}

	/**
	 * 通过反射,获得定义Class时声明的父类的范型参数的类型.
	 * 
	 * @param clazz
	 *            The class to introspect
	 * @return the first generic declaration, or Object.class if cannot be
	 *         determined
	 */
	public static Class getSuperClassGenricType(Class clazz) {
		return getSuperClassGenricType(clazz, 0);
	}

	/**
	 * 通过反射,获得定义Class时声明的父类的范型参数的类型. 如public UserDao extends
	 * HibernateDao<User,Long>
	 * 
	 * @param clazz
	 *            clazz The class to introspect
	 * @param index
	 *            the Index of the generic ddeclaration,start from 0.
	 * @return the index generic declaration, or Object.class if cannot be
	 *         determined
	 */

	public static Class getSuperClassGenricType(Class clazz, int index) {
		Type genType = clazz.getGenericSuperclass();
		if (!(genType instanceof ParameterizedType)) {
			return Object.class;
		}
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		if (index >= params.length || index < 0) {
			return Object.class;
		}
		if (!(params[index] instanceof Class)) {
			return Object.class;
		}
		return (Class) params[index];
	}

	/**
	 * 直接调用对象方法, 无视private/protected修饰符.
	 * 用于一次性调用的情况，否则应使用getAccessibleMethod()函数获得Method后反复调用. 同时匹配方法名+参数类型，
	 */
	public static Object invokeMethod(final Object obj, final String methodName, final Class<?>[] parameterTypes, final Object[] args) {
		Method method = getAccessibleMethod(obj, methodName, parameterTypes);
		if (method == null) {
			throw new IllegalArgumentException("Could not find method [" + methodName + "] on target [" + obj + "]");
		}
		try {
			return method.invoke(obj, args);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	/**
	 * 直接调用对象方法, 无视private/protected修饰符，
	 * 用于一次性调用的情况，否则应使用getAccessibleMethodByName()函数获得Method后反复调用.
	 * 只匹配函数名，如果有多个同名函数调用第一个。
	 */
	public static Object invokeMethodByName(final Object obj, final String methodName, final Object[] args) {
		Method method = getAccessibleMethodByName(obj, methodName);
		if (method == null) {
			throw new IllegalArgumentException("Could not find method [" + methodName + "] on target [" + obj + "]");
		}
		try {
			return method.invoke(obj, args);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	/**
	 * 循环向上转型, 获取对象的DeclaredMethod,并强制设置为可访问. 如向上转型到Object仍无法找到, 返回null.
	 * 匹配函数名+参数类型。
	 * 
	 * 用于方法需要被多次调用的情况. 先使用本函数先取得Method,然后调用Method.invoke(Object obj, Object...
	 * args)
	 */
	public static Method getAccessibleMethod(final Object obj, final String methodName, final Class<?>... parameterTypes) {
		Validate.notNull(obj, "object can't be null");
		Validate.notBlank(methodName, "methodName can't be blank");

		for (Class<?> searchType = obj.getClass(); searchType != Object.class;) {
			try {
				Method method = searchType.getDeclaredMethod(methodName, parameterTypes);
				makeAccessible(method);
				return method;
			} catch (NoSuchMethodException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}
		return null;
	}

	/**
	 * 循环向上转型, 获取对象的DeclaredMethod,并强制设置为可访问. 如向上转型到Object仍无法找到, 返回null. 只匹配函数名。
	 * 
	 * 用于方法需要被多次调用的情况. 先使用本函数先取得Method,然后调用Method.invoke(Object obj, Object...
	 * args)
	 */
	public static Method getAccessibleMethodByName(final Object obj, final String methodName) {
		Validate.notNull(obj, "object can't be null");
		Validate.notBlank(methodName, "methodName can't be blank");
		for (Class<?> searchType = obj.getClass(); searchType != Object.class; searchType = searchType.getSuperclass()) {
			Method[] methods = searchType.getDeclaredMethods();
			for (Method method : methods) {
				if (method.getName().equals(methodName)) {
					makeAccessible(method);
					return method;
				}
			}
		}
		return null;
	}

	/**
	 * 改变private/protected的方法为public。
	 */
	public static void makeAccessible(Method method) {
		if ((!Modifier.isPublic(method.getModifiers()) || !Modifier.isPublic(method.getDeclaringClass().getModifiers())) && !method.isAccessible()) {
			method.setAccessible(true);
		}
	}
	
	/**
	 * 将一个 Map 对象转化为一个 JavaBean
	 * 
	 * @param type
	 * @param map
	 * @return
	 * @throws IntrospectionException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 */
	public static Object convertMap(Class type, Map map) {
		Object obj = null;
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性
			obj = type.newInstance();

			// 给 JavaBean 对象的属性赋值
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (int i = 0; i < propertyDescriptors.length; i++) {
				PropertyDescriptor descriptor = propertyDescriptors[i];
				String propertyName = descriptor.getName();

				if (map.containsKey(propertyName)) {
					// 下面一句可以 try 起来,这样当一个属性赋值失败的时候就不会影响其他属性赋值.
					Object value = map.get(propertyName);

					Object[] args = new Object[1];
					args[0] = value;

					descriptor.getWriteMethod().invoke(obj, args);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return obj;
	}
	
	/**
	 * 将一个 JavaBean 对象转化为一个 Map
	 * 
	 * @param bean
	 * @return
	 * @throws IntrospectionException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static Map<String, Object> convertBean(Object bean) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		try {
			Class<?> type = bean.getClass();
			BeanInfo beanInfo = Introspector.getBeanInfo(type);
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (int i = 0; i < propertyDescriptors.length; i++) {
				PropertyDescriptor descriptor = propertyDescriptors[i];
				String propertyName = descriptor.getName();
				if (!propertyName.equals("class")) {
					Method readMethod = descriptor.getReadMethod();
					//当有set方法，没有属性时readMethod为null 
					if(readMethod!=null){
						Object result = readMethod.invoke(bean, new Object[0]);
						if (result != null) {
							returnMap.put(propertyName, result);
						} // 为空时不写
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		return returnMap;
	}
}
