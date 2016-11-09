package szw.depotms.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import szw.depotms.dao.DriverDAO;
import szw.depotms.model.Driver;
import szw.depotms.service.DriverService;
@Transactional
public class DriverServiceImpl implements DriverService{
	
	@Autowired
	private DriverDAO driverDAO ;

	@Override
	public Driver get(Class<Driver> entityClazz, Serializable id) {
		return driverDAO.get(entityClazz, id) ;
	}

	@Override
	public Serializable save(Driver entity) {
		return driverDAO.save(entity) ;
	}

	@Override
	public void update(Driver entity) {
		driverDAO.update(entity);
	}

	@Override
	public void delete(Driver entity) {
		driverDAO.delete(entity);
	}

	@Override
	public void delete(Class<Driver> entityClazz, Serializable id) {
		driverDAO.delete(entityClazz, id);
	}

	@Override
	public List<Driver> findAll(Class<Driver> entityClazz) {
		return driverDAO.findAll(entityClazz) ;
	}

	@Override
	public long findCount(Class<Driver> entityClazz) {
		return driverDAO.findCount(entityClazz) ;
	}
	
	public List<Driver> find(String hql) {
		return driverDAO.find(hql) ;
	}
	
	public List<Driver> find(String hql, Object[] params) {
		return driverDAO.find(hql, params) ;
	}
	
	public List<Driver> findByPage(String hql, int pageNo, int pageSize)  {
		return driverDAO.findByPage(hql, pageNo, pageSize) ;
	}
	
	public List<Driver> findByPage(String hql, int pageNo, int pageSize, Object[] params) {
		return driverDAO.findByPage(hql, pageNo, pageSize, params) ;
	}

	public DriverDAO getDriverDAO() {
		return driverDAO;
	}

	public void setDriverDAO(DriverDAO driverDAO) {
		this.driverDAO = driverDAO;
	}

}
