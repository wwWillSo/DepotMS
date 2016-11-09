package szw.depotms.service;

import java.io.Serializable;
import java.util.List;

import szw.depotms.model.DayArrange;

public interface DayArrangeService {
	public DayArrange get(Class<DayArrange> entityClazz, Serializable id) ;
	
	public Serializable save(DayArrange entity) ;
	
	public void update(DayArrange entity) ;
	
	public void delete(DayArrange entity) ;
	
	public void delete(Class<DayArrange> entityClazz, Serializable id) ;
	
	public List<DayArrange> findAll(Class<DayArrange> entityClazz) ;
	
	public long findCount(Class<DayArrange> entityClazz) ;
	
	public List<DayArrange> find(String hql) ;
}
