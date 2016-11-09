package szw.depotms.dao;

import java.util.List;

import szw.depotms.model.Staff;

public interface StaffDAO extends BaseDAO<Staff> {
	public List<Staff> find(String hql) ;
	
	public List<Staff> find(String hql, Object[] params) ;
	
	public List<Staff> findByPage(String hql, int pageNo, int pageSize) ;
	
	public List<Staff> findByPage(String hql, int pageNo, int pageSize, Object[] params) ;
	
}
