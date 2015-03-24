package net.dubrouski.fams.dao;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author stanislau.dubrouski
 *
 * @param <T>
 * @param <ID>
 */
public interface BaseDao<T, ID extends Serializable> {
	public T getByID(ID id);

	public void save(T entity);
	
	public Collection<T> listAll();

	public Class<T> getPersistentClass();

	public void setPersistentClass(Class<T> persistentClass);

}
