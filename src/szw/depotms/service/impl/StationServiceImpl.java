package szw.depotms.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import szw.depotms.dao.StationDAO;
import szw.depotms.model.Station;
import szw.depotms.service.StationService;

@Transactional
public class StationServiceImpl implements StationService{
	
	@Autowired
	private StationDAO stationDAO ;

	@Override
	public Station get(Class<Station> entityClazz, Serializable id) {
		return stationDAO.get(entityClazz, id) ;
	}

	@Override
	public Serializable save(Station entity) {
		return stationDAO.save(entity) ;
	}

	@Override
	public void update(Station entity) {
		stationDAO.update(entity);
	}

	@Override
	public void delete(Station entity) {
		stationDAO.delete(entity);
	}

	@Override
	public void delete(Class<Station> entityClazz, Serializable id) {
		stationDAO.delete(entityClazz, id);
	}

	@Override
	public List<Station> findAll(Class<Station> entityClazz) {
		return stationDAO.findAll(entityClazz) ;
	}

	@Override
	public long findCount(Class<Station> entityClazz) {
		return stationDAO.findCount(entityClazz) ;
	}

	public StationDAO getStationDAO() {
		return stationDAO;
	}

	public void setStationDAO(StationDAO stationDAO) {
		this.stationDAO = stationDAO;
	}
	
	public List<Station> find(String hql) {
		return stationDAO.find(hql) ;
	}
	
	public List<Station> find(String hql, Object[] params) {
		return stationDAO.find(hql, params) ;
	}
	
	public List<Station> findByPage(String hql, int pageNo, int pageSize)  {
		return stationDAO.findByPage(hql, pageNo, pageSize) ;
	}
	
	public List<Station> findByPage(String hql, int pageNo, int pageSize, Object[] params) {
		return stationDAO.findByPage(hql, pageNo, pageSize, params) ;
	}

}
