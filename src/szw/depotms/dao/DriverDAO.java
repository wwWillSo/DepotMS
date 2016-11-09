package szw.depotms.dao;

import java.util.List;

import szw.depotms.model.Driver;

public interface DriverDAO extends BaseDAO<Driver>{
	public List<Driver> find(String hql) ;
	
	public List<Driver> find(String hql, Object[] params) ;
	
	public List<Driver> findByPage(String hql, int pageNo, int pageSize) ;
	
	public List<Driver> findByPage(String hql, int pageNo, int pageSize, Object[] params) ;
	
}
