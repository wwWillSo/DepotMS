package szw.depotms.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import szw.depotms.dao.StationOfLineDAO;
import szw.depotms.model.StationOfLine;
import szw.depotms.service.StationOfLineService;

public class StationOfLineServiceImpl implements StationOfLineService{
	
	@Autowired
	private StationOfLineDAO stationOfLineDAO ;

	@Override
	public StationOfLine get(Class<StationOfLine> entityClazz, Serializable id) {
		return stationOfLineDAO.get(entityClazz, id) ;
	}

	@Override
	public Serializable save(StationOfLine entity) {
		return stationOfLineDAO.save(entity) ;
	}

	@Override
	public void update(StationOfLine entity) {
		stationOfLineDAO.update(entity);
	}

	@Override
	public void delete(StationOfLine entity) {
		stationOfLineDAO.delete(entity);
	}

	@Override
	public void delete(Class<StationOfLine> entityClazz, Serializable id) {
		stationOfLineDAO.delete(entityClazz, id);
	}

	@Override
	public List<StationOfLine> findAll(Class<StationOfLine> entityClazz) {
		return stationOfLineDAO.findAll(entityClazz) ;
	}

	@Override
	public long findCount(Class<StationOfLine> entityClazz) {
		return stationOfLineDAO.findCount(entityClazz) ;
	}

	public List<StationOfLine> find(String hql) {
		return stationOfLineDAO.find(hql) ;
	}
	
	public List<StationOfLine> find(String hql, Object[] params) {
		return stationOfLineDAO.find(hql, params) ;
	}
	
	public List<StationOfLine> findByPage(String hql, int pageNo, int pageSize)  {
		return stationOfLineDAO.findByPage(hql, pageNo, pageSize) ;
	}
	
	public List<StationOfLine> findByPage(String hql, int pageNo, int pageSize, Object[] params) {
		return stationOfLineDAO.findByPage(hql, pageNo, pageSize, params) ;
	}

	public StationOfLineDAO getStationOfLineDAO() {
		return stationOfLineDAO;
	}

	public void setStationOfLineDAO(StationOfLineDAO stationOfLineDAO) {
		this.stationOfLineDAO = stationOfLineDAO;
	}

	//根据lineId与stationId删除记录
	public void delete(Class<StationOfLine> entityClazz, Serializable lineId, Serializable stationId) {
		this.stationOfLineDAO.delete(entityClazz, lineId, stationId);
	}
}
