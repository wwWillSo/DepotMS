package szw.depotms.dao;

import java.io.Serializable;
import java.util.List;

import szw.depotms.model.StationOfLine;

public interface StationOfLineDAO extends BaseDAO<StationOfLine> {
	public List<StationOfLine> find(String hql) ;
	
	public List<StationOfLine> find(String hql, Object[] params) ;
	
	public List<StationOfLine> findByPage(String hql, int pageNo, int pageSize) ;
	
	public List<StationOfLine> findByPage(String hql, int pageNo, int pageSize, Object[] params) ;
	
	//����lineId��stationIdɾ����¼
	public void delete(Class<StationOfLine> entityClazz, Serializable lineId, Serializable stationId) ;
	
}
