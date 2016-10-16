package com.free.love.core.manager;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.free.love.core.dao.BaseDAO;

public abstract interface BaseManager
{
  public abstract void setDao(BaseDAO paramBaseDAO);


  public abstract String getIdentifierName(Class<?> paramClass);

  public abstract <E> E getIdentifierValue(Object paramObject);

  public abstract void save(Object paramObject);

  public abstract void save(Object[] paramArrayOfObject);

  public abstract void save(Collection<?> paramCollection);

  public abstract void delete(Object paramObject);

  public abstract void delete(Object[] paramArrayOfObject);

  public abstract void delete(Collection<?> paramCollection);

  public abstract void refresh(Object paramObject);

  public abstract <E> List<E> retrieve(Class<E> paramClass);

  public abstract <E> E retrieve(Class<E> paramClass, Serializable paramSerializable);

  public abstract <E> List<E> retrieve(Class<E> paramClass, String paramString, Serializable paramSerializable);


  public abstract <E> List<E> namedQuery(String paramString);

  public abstract <E> List<E> namedQuery(String paramString1, String paramString2, Object paramObject);

  public abstract <E> List<E> namedQuery(String paramString, String[] paramArrayOfString, Object[] paramArrayOfObject);

  public abstract <E> E getFirst(List<E> paramList);
}

