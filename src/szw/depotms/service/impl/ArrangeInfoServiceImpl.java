package szw.depotms.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import szw.depotms.dao.ArrangeInfoDAO;
import szw.depotms.model.ArrangeInfo;
import szw.depotms.service.ArrangeInfoService;
@Transactional
public class ArrangeInfoServiceImpl implements ArrangeInfoService{
	
	@Autowired
	private ArrangeInfoDAO arrangeInfoDAO ;

	@Override
	public ArrangeInfo get(Class<ArrangeInfo> entityClazz, Serializable id) {
		return arrangeInfoDAO.get(entityClazz, id) ;
	}

	@Override
	public Serializable save(ArrangeInfo entity) {
		return arrangeInfoDAO.save(entity) ;
	}

	@Override
	public void update(ArrangeInfo entity) {
		arrangeInfoDAO.update(entity);
	}

	@Override
	public void delete(ArrangeInfo entity) {
		arrangeInfoDAO.delete(entity);
	}

	@Override
	public void delete(Class<ArrangeInfo> entityClazz, Serializable id) {
		arrangeInfoDAO.delete(entityClazz, id);
	}

	@Override
	public List<ArrangeInfo> findAll(Class<ArrangeInfo> entityClazz) {
		return arrangeInfoDAO.findAll(entityClazz) ;
	}

	@Override
	public long findCount(Class<ArrangeInfo> entityClazz) {
		return arrangeInfoDAO.findCount(entityClazz) ;
	}
	
	public List<ArrangeInfo> find(String hql) {
		return arrangeInfoDAO.find(hql) ;
	}
	
	public List<ArrangeInfo> find(String hql, Object[] params) {
		return arrangeInfoDAO.find(hql, params) ;
	}
	
	public List<ArrangeInfo> findByPage(String hql, int pageNo, int pageSize)  {
		return arrangeInfoDAO.findByPage(hql, pageNo, pageSize) ;
	}
	
	public List<ArrangeInfo> findByPage(String hql, int pageNo, int pageSize, Object[] params) {
		return arrangeInfoDAO.findByPage(hql, pageNo, pageSize, params) ;
	}

	public ArrangeInfoDAO getArrangeInfoDAO() {
		return arrangeInfoDAO;
	}

	public void setArrangeInfoDAO(ArrangeInfoDAO arrangeInfoDAO) {
		this.arrangeInfoDAO = arrangeInfoDAO;
	}

}
