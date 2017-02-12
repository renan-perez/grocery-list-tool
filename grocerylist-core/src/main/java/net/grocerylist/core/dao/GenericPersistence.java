package net.grocerylist.core.dao;

import net.grocerylist.core.exception.SystemException;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface GenericPersistence<T, ID extends Serializable> {

    T save(final T instance) throws SystemException;

    void delete(T t) throws SystemException;

    T get(final Class<T> clazz, final ID id) throws SystemException;

    List<T> list(final Class<T> clazz) throws SystemException;

    T getByNamedQuery(final String namedQuery, final Map<String,Object> paramValueMap, final Class<T> clazz) throws SystemException;

    List<T> listByNamedQuery(final String namedQuery, final Map<String,Object> paramValueMap, final Class<T> clazz) throws SystemException;
}
