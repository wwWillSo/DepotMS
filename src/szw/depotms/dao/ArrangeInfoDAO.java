package szw.depotms.dao;

import java.util.List;

import szw.depotms.model.ArrangeInfo;

public interface ArrangeInfoDAO extends BaseDAO<ArrangeInfo>{
	public List<ArrangeInfo> find(String hql) ;
	
	public List<ArrangeInfo> find(String hql, Object[] params) ;
	
	public List<ArrangeInfo> findByPage(String hql, int pageNo, int pageSize) ;
	
	public List<ArrangeInfo> findByPage(String hql, int pageNo, int pageSize, Object[] params) ;
	
}
