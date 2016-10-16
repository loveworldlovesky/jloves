package com.free.love.core.beanutil;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class GenericsUtils {
	public static <E> Class<E> getGenericClass(Class<?> clazz) {
		return getGenericClass(clazz, 0);
	}

	public static <E> Class<E> getGenericClass(Class<?> clazz, int index)
			throws IndexOutOfBoundsException {
		Class[] classes = getGenericClasses(clazz);

		if (classes == null) {
			return null;
		}
		if ((index >= classes.length) || (index < 0)) {
			throw new IndexOutOfBoundsException("Index: " + index
					+ ", Size of Parameterized Type: " + classes.length);
		}
		return classes[index];
	}

	public static <E> Class<E>[] getGenericClasses(Class<?> clazz) {
		Type genType = null;

		while (!clazz.getName().equals(Object.class.getName())) {
			genType = clazz.getGenericSuperclass();
			if ((genType instanceof ParameterizedType)) {
				break;
			}
			clazz = clazz.getSuperclass();
		}
		if (clazz.getName().equals(Object.class.getName())) {
			return null;
		}

		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		Class[] classes = new Class[params.length];

		for (int i = 0; i < params.length; i++) {
			if (Class.class.isAssignableFrom(params[i].getClass()))
				classes[i] = ((Class) params[i]);
		}
		return classes;
	}
}
