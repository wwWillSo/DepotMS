package szw.depotms.service;

import java.io.Serializable;
import java.util.List;

import szw.depotms.model.Dept;

public interface DeptService {
	public Dept get(Class<Dept> entityClazz, Serializable id) ;
	
	public Serializable save(Dept entity) ;
	
	public void update(Dept entity) ;
	
	public void delete(Dept entity) ;
	
	public void delete(Class<Dept> entityClazz, Serializable id) ;
	
	public List<Dept> findAll(Class<Dept> entityClazz) ;
	
	public long findCount(Class<Dept> entityClazz) ;
}
