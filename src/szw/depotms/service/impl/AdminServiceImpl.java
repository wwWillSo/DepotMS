package szw.depotms.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import szw.depotms.dao.AdminDAO;
import szw.depotms.model.Admin;
import szw.depotms.service.AdminService;

public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminDAO adminDAO ;

	@Override
	public Admin get(Class<Admin> entityClazz, Serializable id) {
		return adminDAO.get(entityClazz, id) ;
	}

	@Override
	public Serializable save(Admin entity) {
		return adminDAO.save(entity) ;
	}

	@Override
	public void update(Admin entity) {
		adminDAO.update(entity);
	}

	@Override
	public void delete(Admin entity) {
		adminDAO.delete(entity);
	}

	@Override
	public void delete(Class<Admin> entityClazz, Serializable id) {
		adminDAO.delete(entityClazz, id);
	}

	@Override
	public List<Admin> findAll(Class<Admin> entityClazz) {
		return adminDAO.findAll(entityClazz) ;
	}

	@Override
	public long findCount(Class<Admin> entityClazz) {
		return adminDAO.findCount(entityClazz) ;
	}

	public AdminDAO getAdminDAO() {
		return adminDAO;
	}

	public void setAdminDAO(AdminDAO adminDAO) {
		this.adminDAO = adminDAO;
	}

}
