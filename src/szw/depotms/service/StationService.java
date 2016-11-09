package szw.depotms.service;

import java.io.Serializable;
import java.util.List;

import szw.depotms.model.Station;

public interface StationService {
	public Station get(Class<Station> entityClazz, Serializable id) ;
	
	public Serializable save(Station entity) ;
	
	public void update(Station entity) ;
	
	public void delete(Station entity) ;
	
	public void delete(Class<Station> entityClazz, Serializable id) ;
	
	public List<Station> findAll(Class<Station> entityClazz) ;
	
	public long findCount(Class<Station> entityClazz) ;
	
	public List<Station> find(String hql) ;
	
	public List<Station> find(String hql, Object[] params) ;
	
	public List<Station> findByPage(String hql, int pageNo, int pageSize) ;
	
	public List<Station> findByPage(String hql, int pageNo, int pageSize, Object[] params) ;
	
}
