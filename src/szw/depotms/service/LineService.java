package szw.depotms.service;

import java.io.Serializable;
import java.util.List;

import szw.depotms.model.Line;

public interface LineService {
	public Line get(Class<Line> entityClazz, Serializable id) ;
	
	public Serializable save(Line entity) ;
	
	public void update(Line entity) ;
	
	public void delete(Line entity) ;
	
	public void delete(Class<Line> entityClazz, Serializable id) ;
	
	public List<Line> findAll(Class<Line> entityClazz) ;
	
	public long findCount(Class<Line> entityClazz) ;
	
}
