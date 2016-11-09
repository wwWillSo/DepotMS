package szw.depotms.service;

import java.io.Serializable;
import java.util.List;

import szw.depotms.model.WeekArrange;

public interface WeekArrangeService {
	public WeekArrange get(Class<WeekArrange> entityClazz, Serializable id) ;
	
	public Serializable save(WeekArrange entity) ;
	
	public void update(WeekArrange entity) ;
	
	public void delete(WeekArrange entity) ;
	
	public void delete(Class<WeekArrange> entityClazz, Serializable id) ;
	
	public List<WeekArrange> findAll(Class<WeekArrange> entityClazz) ;
	
	public long findCount(Class<WeekArrange> entityClazz) ;
	
	public List<WeekArrange> find(String hql) ;
	
}
