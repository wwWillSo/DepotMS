package szw.depotms.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import szw.depotms.dao.DayArrangeDAO;
import szw.depotms.model.DayArrange;
import szw.depotms.service.DayArrangeService;

public class DayArrangeServiceImpl implements DayArrangeService{
	
	@Autowired
	private DayArrangeDAO dayArrangeDAO ;

	@Override
	public DayArrange get(Class<DayArrange> entityClazz, Serializable id) {
		return dayArrangeDAO.get(entityClazz, id) ;
	}

	@Override
	public Serializable save(DayArrange entity) {
		return dayArrangeDAO.save(entity) ;
	}

	@Override
	public void update(DayArrange entity) {
		dayArrangeDAO.update(entity);
	}

	@Override
	public void delete(DayArrange entity) {
		dayArrangeDAO.delete(entity);
	}

	@Override
	public void delete(Class<DayArrange> entityClazz, Serializable id) {
		dayArrangeDAO.delete(entityClazz, id);
	}

	@Override
	public List<DayArrange> findAll(Class<DayArrange> entityClazz) {
		return dayArrangeDAO.findAll(entityClazz) ;
	}

	@Override
	public long findCount(Class<DayArrange> entityClazz) {
		return dayArrangeDAO.findCount(entityClazz) ;
	}

	public DayArrangeDAO getDayArrangeDAO() {
		return dayArrangeDAO;
	}

	public void setDayArrangeDAO(DayArrangeDAO dayArrangeDAO) {
		this.dayArrangeDAO = dayArrangeDAO;
	}
	
	public List<DayArrange> find(String hql) {
		return dayArrangeDAO.find(hql) ;
	}
}
