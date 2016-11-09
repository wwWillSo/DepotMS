package szw.depotms.service;

import java.io.Serializable;
import java.util.List;

import szw.depotms.model.Admin;

public interface AdminService {
	public Admin get(Class<Admin> entityClazz, Serializable id) ;
	
	public Serializable save(Admin entity) ;
	
	public void update(Admin entity) ;
	
	public void delete(Admin entity) ;
	
	public void delete(Class<Admin> entityClazz, Serializable id) ;
	
	public List<Admin> findAll(Class<Admin> entityClazz) ;
	
	public long findCount(Class<Admin> entityClazz) ;
	
}
