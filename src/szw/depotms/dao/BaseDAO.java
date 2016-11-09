package szw.depotms.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDAO<T> {
	public T get(Class<T> entityClazz, Serializable id) ;
	
	public Serializable save(T entity) ;
	
	public void update(T entity) ;
	
	public void delete(T entity) ;
	
	public void delete(Class<T> entityClazz, Serializable id) ;
	
	public List<T> findAll(Class<T> entityClazz) ;
	
	public long findCount(Class<T> entityClazz) ;
	
	public List<T> find(String hql) ;
	
	public List<T> find(String hql, Object[] params) ;
	
	public List<T> findByPage(String hql, int pageNo, int pageSize) ;
	
	public List<T> findByPage(String hql, int pageNo, int pageSize, Object[] params) ;
}
