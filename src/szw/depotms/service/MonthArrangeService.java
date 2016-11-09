package szw.depotms.service;

import java.io.Serializable;
import java.util.List;

import szw.depotms.model.MonthArrange;

public interface MonthArrangeService {
	public MonthArrange get(Class<MonthArrange> entityClazz, Serializable id) ;
	
	public Serializable save(MonthArrange entity) ;
	
	public void update(MonthArrange entity) ;
	
	public void delete(MonthArrange entity) ;
	
	public void delete(Class<MonthArrange> entityClazz, Serializable id) ;
	
	public List<MonthArrange> findAll(Class<MonthArrange> entityClazz) ;
	
	public long findCount(Class<MonthArrange> entityClazz) ;
	
	public List<MonthArrange> find(String sql) ;
	
}
