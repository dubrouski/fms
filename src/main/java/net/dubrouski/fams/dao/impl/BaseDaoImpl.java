package net.dubrouski.fams.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import net.dubrouski.fams.dao.BaseDao;

/**
 * @author stanislau.dubrouski
 *
 */
@Named(value = "baseDao")
public class BaseDaoImpl<T, ID extends Serializable> implements BaseDao<T, ID> {
	@Inject
	protected EntityManager entityManager;

	protected Class<T> persistentClass;

	@Override
	public T getByID(ID id) {
		return entityManager.find(getPersistentClass(), id);
	}

	@Override
	public void save(T entity) {
		entityManager.persist(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Class<T> getPersistentClass() {
		if (persistentClass == null) {
			persistentClass = (Class<T>) ((ParameterizedType) getClass()
					.getGenericSuperclass()).getActualTypeArguments()[0];
		}
		return persistentClass;
	}

	@Override
	@Deprecated
	public void setPersistentClass(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}

}
