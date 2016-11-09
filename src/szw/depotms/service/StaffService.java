package szw.depotms.service;

import java.io.Serializable;
import java.util.List;

import szw.depotms.model.Staff;

public interface StaffService {
	public Staff get(Class<Staff> entityClazz, Serializable id) ;
	
	public Serializable save(Staff entity) ;
	
	public void update(Staff entity) ;
	
	public void delete(Staff entity) ;
	
	public void delete(Class<Staff> entityClazz, Serializable id) ;
	
	public List<Staff> findAll(Class<Staff> entityClazz) ;
	
	public long findCount(Class<Staff> entityClazz) ;
	
	public List<Staff> find(String hql) ;
	
	public List<Staff> find(String hql, Object[] params) ;
	
	public List<Staff> findByPage(String hql, int pageNo, int pageSize) ;
	
	public List<Staff> findByPage(String hql, int pageNo, int pageSize, Object[] params) ;
	
}
