package szw.depotms.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import szw.depotms.dao.WeekArrangeDAO;
import szw.depotms.model.WeekArrange;
import szw.depotms.service.WeekArrangeService;

public class WeekArrangeServiceImpl implements WeekArrangeService{
	
	@Autowired
	private WeekArrangeDAO weekArrangeDAO ;

	@Override
	public WeekArrange get(Class<WeekArrange> entityClazz, Serializable id) {
		return weekArrangeDAO.get(entityClazz, id) ;
	}

	@Override
	public Serializable save(WeekArrange entity) {
		return weekArrangeDAO.save(entity) ;
	}

	@Override
	public void update(WeekArrange entity) {
		weekArrangeDAO.update(entity);
	}

	@Override
	public void delete(WeekArrange entity) {
		weekArrangeDAO.delete(entity);
	}

	@Override
	public void delete(Class<WeekArrange> entityClazz, Serializable id) {
		weekArrangeDAO.delete(entityClazz, id);
	}

	@Override
	public List<WeekArrange> findAll(Class<WeekArrange> entityClazz) {
		return weekArrangeDAO.findAll(entityClazz) ;
	}

	@Override
	public long findCount(Class<WeekArrange> entityClazz) {
		return weekArrangeDAO.findCount(entityClazz) ;
	}

	public WeekArrangeDAO getWeekArrangeDAO() {
		return weekArrangeDAO;
	}

	public void setWeekArrangeDAO(WeekArrangeDAO weekArrangeDAO) {
		this.weekArrangeDAO = weekArrangeDAO;
	}
	
	public List<WeekArrange> find(String hql) {
		return weekArrangeDAO.find(hql) ;
	}

}
