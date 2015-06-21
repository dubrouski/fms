package net.dubrouski.fams.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import net.dubrouski.fams.dao.BaseDao;
import net.dubrouski.fams.filter.GeneralSearchFilter;
import net.dubrouski.fams.filter.SearchFilter;
import net.dubrouski.fams.model.Person;

/**
 * @author stanislau.dubrouski
 *
 */
@Named(value = "baseDao")
public abstract class BaseDaoImpl<T, ID extends Serializable> implements
		BaseDao<T, ID> {
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

	@SuppressWarnings("unchecked")
	@Override
	public List<T> listAll() {
		return (List<T>) entityManager.createQuery(
				"FROM " + getPersistentClass().getName()).getResultList();
	}

	@Override
	public void update(T entity) {
		entityManager.merge(entity);
	}

	@Override
	public void delete(T entity) {
		// TODO discuss
		// http://stackoverflow.com/questions/17027398/java-lang-illegalargumentexception-removing-a-detached-instance-com-test-user5
		entityManager.remove(entityManager.contains(entity) ? entity
				: entityManager.merge(entity));
	}
}
