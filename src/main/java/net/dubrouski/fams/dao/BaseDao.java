package net.dubrouski.fams.dao;

import java.io.Serializable;
import java.util.List;

/**
 * @author stanislau.dubrouski
 *
 * @param <T>
 * @param <ID>
 */
public interface BaseDao<T, ID extends Serializable> {
	public T getByID(ID id);

	public void save(T entity);
	
	public List<T> listAll();

	public Class<T> getPersistentClass();

	public void setPersistentClass(Class<T> persistentClass);

	public void delete(T entity);
	
	public void update(T entity);
	
}
