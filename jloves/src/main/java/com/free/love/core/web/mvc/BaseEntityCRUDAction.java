package com.free.love.core.web.mvc;

import com.free.love.core.beanutil.GenericsUtils;
import com.free.love.core.manager.BaseManager;

public class BaseEntityCRUDAction<T> {
	private Class<?> entityClass;
	private BaseManager entityManager;
	private String themeId = null;

	public BaseEntityCRUDAction() {
		this.entityClass = GenericsUtils.getGenericClass(getClass());
	}

}
