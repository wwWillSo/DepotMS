package szw.depotms.service;

import java.io.Serializable;
import java.util.List;

import szw.depotms.model.Classes;

public interface ClassesService {
	public Classes get(Class<Classes> entityClazz, Serializable id) ;
	
	public Serializable save(Classes entity) ;
	
	public void update(Classes entity) ;
	
	public void delete(Classes entity) ;
	
	public void delete(Class<Classes> entityClazz, Serializable id) ;
	
	public List<Classes> findAll(Class<Classes> entityClazz) ;
	
	public long findCount(Class<Classes> entityClazz) ;
	
}
