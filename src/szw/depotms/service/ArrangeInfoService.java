package szw.depotms.service;

import java.io.Serializable;
import java.util.List;

import szw.depotms.model.ArrangeInfo;

public interface ArrangeInfoService {
	public ArrangeInfo get(Class<ArrangeInfo> entityClazz, Serializable id) ;
	
	public Serializable save(ArrangeInfo entity) ;
	
	public void update(ArrangeInfo entity) ;
	
	public void delete(ArrangeInfo entity) ;
	
	public void delete(Class<ArrangeInfo> entityClazz, Serializable id) ;
	
	public List<ArrangeInfo> findAll(Class<ArrangeInfo> entityClazz) ;
	
	public long findCount(Class<ArrangeInfo> entityClazz) ;
	
	public List<ArrangeInfo> find(String hql) ;
	
	public List<ArrangeInfo> find(String hql, Object[] params) ;
	
	public List<ArrangeInfo> findByPage(String hql, int pageNo, int pageSize) ;
	
	public List<ArrangeInfo> findByPage(String hql, int pageNo, int pageSize, Object[] params) ;
	
}
