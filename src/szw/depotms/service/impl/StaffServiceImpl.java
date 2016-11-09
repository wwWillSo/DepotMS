package szw.depotms.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import szw.depotms.dao.StaffDAO;
import szw.depotms.model.Staff;
import szw.depotms.service.StaffService;
@Transactional
public class StaffServiceImpl implements StaffService{
	
	@Autowired
	private StaffDAO staffDAO ;

	@Override
	public Staff get(Class<Staff> entityClazz, Serializable id) {
		return staffDAO.get(entityClazz, id) ;
	}

	@Override
	public Serializable save(Staff entity) {
		return staffDAO.save(entity) ;
	}

	@Override
	public void update(Staff entity) {
		staffDAO.update(entity);
	}

	@Override
	public void delete(Staff entity) {
		staffDAO.delete(entity);
	}

	@Override
	public void delete(Class<Staff> entityClazz, Serializable id) {
		staffDAO.delete(entityClazz, id);
	}

	@Override
	public List<Staff> findAll(Class<Staff> entityClazz) {
		return staffDAO.findAll(entityClazz) ;
	}

	@Override
	public long findCount(Class<Staff> entityClazz) {
		return staffDAO.findCount(entityClazz) ;
	}
	
	public List<Staff> find(String hql) {
		return staffDAO.find(hql) ;
	}
	
	public List<Staff> find(String hql, Object[] params) {
		return staffDAO.find(hql, params) ;
	}
	
	public List<Staff> findByPage(String hql, int pageNo, int pageSize)  {
		return staffDAO.findByPage(hql, pageNo, pageSize) ;
	}
	
	public List<Staff> findByPage(String hql, int pageNo, int pageSize, Object[] params) {
		return staffDAO.findByPage(hql, pageNo, pageSize, params) ;
	}

	public StaffDAO getStaffDAO() {
		return staffDAO;
	}

	public void setStaffDAO(StaffDAO staffDAO) {
		this.staffDAO = staffDAO;
	}

}
