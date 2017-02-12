package net.grocerylist.core.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.grocerylist.core.exception.SystemException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class GenericDAO<T, ID extends Serializable> {

    @PersistenceContext
    private EntityManager manager;
    
    @Transactional
    protected T save(final T instance) throws SystemException {
        manager.persist(instance);
        manager.flush();
        return instance;
    }
    
    protected void saveAll(final Collection<T> AllInstances) throws SystemException {
    	AllInstances.forEach(manager::persist);
        manager.flush();
    }
    
    @Transactional
    protected T update(final T instance) throws SystemException {
    	T t = manager.merge(instance);
    	manager.flush();
    	return t;
    }

    protected void delete(T instance) throws SystemException {
        manager.remove(instance);
        manager.flush();
    }
    
    protected void deleteAll(final Collection<T> AllInstances) throws SystemException {
    	AllInstances.forEach(manager::remove);
        manager.flush();
    }

    protected T get(final Class<T> clazz, final ID id) throws SystemException {
        return manager.find(clazz, id);
    }

    protected Collection<T> list(final Class<T> clazz) throws SystemException {
        return listByNamedQuery(clazz.getSimpleName() + ".list", null, clazz);
    }

    @SuppressWarnings("unchecked")
	protected Collection<T> listByNamedQuery(final String namedQuery,
                                       final Map<String, Object> paramValueMap, final Class<T> clazz) throws SystemException {
    	try {
    		return createQuery(namedQuery, paramValueMap, clazz).getResultList();
    	} catch (NoResultException e) {
			return null;
		}
    }
    
    @SuppressWarnings("unchecked")
    protected T getByNamedQuery(final String namedQuery,
                                final Map<String, Object> paramValueMap, final Class<T> clazz) throws SystemException {
    	try {
    		return (T) createQuery(namedQuery, paramValueMap, clazz).setMaxResults(1).getSingleResult();
    	} catch (NoResultException e) {
			return null;
		}
    }
    
    protected void executeNamedQuery(final String namedQuery,
                                final Map<String, Object> paramValueMap, final Class<T> clazz) throws SystemException {
    	createQuery(namedQuery, paramValueMap, clazz).setMaxResults(1).getSingleResult();
    }

    private Query createQuery(final String namedQuery, final Map<String, Object> paramValueMap, final Class<T> clazz) {
        Query query = manager.createNamedQuery(namedQuery, clazz);
        if (!Objects.isNull(paramValueMap)) {
            paramValueMap.forEach((name, param) -> query.setParameter(name, param));
        }
        return query;
    }
}