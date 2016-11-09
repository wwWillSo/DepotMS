package szw.depotms.service;

import java.io.Serializable;
import java.util.List;

import szw.depotms.model.Driver;

public interface DriverService {
	public Driver get(Class<Driver> entityClazz, Serializable id) ;
	
	public Serializable save(Driver entity) ;
	
	public void update(Driver entity) ;
	
	public void delete(Driver entity) ;
	
	public void delete(Class<Driver> entityClazz, Serializable id) ;
	
	public List<Driver> findAll(Class<Driver> entityClazz) ;
	
	public long findCount(Class<Driver> entityClazz) ;
	
	public List<Driver> find(String hql) ;
	
	public List<Driver> find(String hql, Object[] params) ;
	
	public List<Driver> findByPage(String hql, int pageNo, int pageSize) ;
	
	public List<Driver> findByPage(String hql, int pageNo, int pageSize, Object[] params) ;
	
}
