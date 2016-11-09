package szw.depotms.service;

import java.io.Serializable;
import java.util.List;

import szw.depotms.model.StationOfLine;

public interface StationOfLineService {
	public StationOfLine get(Class<StationOfLine> entityClazz, Serializable id) ;
	
	public Serializable save(StationOfLine entity) ;
	
	public void update(StationOfLine entity) ;
	
	public void delete(StationOfLine entity) ;
	
	public void delete(Class<StationOfLine> entityClazz, Serializable id) ;
	
	public List<StationOfLine> findAll(Class<StationOfLine> entityClazz) ;
	
	public long findCount(Class<StationOfLine> entityClazz) ;
		
	public List<StationOfLine> find(String hql) ;
	
	public List<StationOfLine> find(String hql, Object[] params) ;
	
	public List<StationOfLine> findByPage(String hql, int pageNo, int pageSize) ;
	
	public List<StationOfLine> findByPage(String hql, int pageNo, int pageSize, Object[] params) ;
	
	//根据lineId与stationId删除记录
	public void delete(Class<StationOfLine> entityClazz, Serializable lineId, Serializable stationId) ;
}
