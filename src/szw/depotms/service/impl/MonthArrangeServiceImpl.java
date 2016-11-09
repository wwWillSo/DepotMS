package szw.depotms.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import szw.depotms.dao.MonthArrangeDAO;
import szw.depotms.model.MonthArrange;
import szw.depotms.service.MonthArrangeService;

public class MonthArrangeServiceImpl implements MonthArrangeService{
	
	@Autowired
	private MonthArrangeDAO monthArrangeDAO ;

	@Override
	public MonthArrange get(Class<MonthArrange> entityClazz, Serializable id) {
		return monthArrangeDAO.get(entityClazz, id) ;
	}

	@Override
	public Serializable save(MonthArrange entity) {
		return monthArrangeDAO.save(entity) ;
	}

	@Override
	public void update(MonthArrange entity) {
		monthArrangeDAO.update(entity);
	}

	@Override
	public void delete(MonthArrange entity) {
		monthArrangeDAO.delete(entity);
	}

	@Override
	public void delete(Class<MonthArrange> entityClazz, Serializable id) {
		monthArrangeDAO.delete(entityClazz, id);
	}

	@Override
	public List<MonthArrange> findAll(Class<MonthArrange> entityClazz) {
		return monthArrangeDAO.findAll(entityClazz) ;
	}

	@Override
	public long findCount(Class<MonthArrange> entityClazz) {
		return monthArrangeDAO.findCount(entityClazz) ;
	}

	public MonthArrangeDAO getMonthArrangeDAO() {
		return monthArrangeDAO;
	}

	public void setMonthArrangeDAO(MonthArrangeDAO monthArrangeDAO) {
		this.monthArrangeDAO = monthArrangeDAO;
	}
	
	public List<MonthArrange> find(String hql) {
		return monthArrangeDAO.find(hql) ;
	}

}
