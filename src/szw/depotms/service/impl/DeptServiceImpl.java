package szw.depotms.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import szw.depotms.dao.DeptDAO;
import szw.depotms.model.Dept;
import szw.depotms.service.DeptService;
@Transactional
public class DeptServiceImpl implements DeptService{
	
	@Autowired
	private DeptDAO deptDAO ;

	@Override
	public Dept get(Class<Dept> entityClazz, Serializable id) {
		return deptDAO.get(entityClazz, id) ;
	}

	@Override
	public Serializable save(Dept entity) {
		return deptDAO.save(entity) ;
	}

	@Override
	public void update(Dept entity) {
		deptDAO.update(entity);
	}

	@Override
	public void delete(Dept entity) {
		deptDAO.delete(entity);
	}

	@Override
	public void delete(Class<Dept> entityClazz, Serializable id) {
		deptDAO.delete(entityClazz, id);
	}

	@Override
	public List<Dept> findAll(Class<Dept> entityClazz) {
		return deptDAO.findAll(entityClazz) ;
	}

	@Override
	public long findCount(Class<Dept> entityClazz) {
		return deptDAO.findCount(entityClazz) ;
	}

	public DeptDAO getDeptDAO() {
		return deptDAO;
	}

	public void setDeptDAO(DeptDAO deptDAO) {
		this.deptDAO = deptDAO;
	}

}
