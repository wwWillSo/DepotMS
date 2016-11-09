package szw.depotms.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import szw.depotms.dao.LineDAO;
import szw.depotms.model.Line;
import szw.depotms.service.LineService;
@Transactional
public class LineServiceImpl implements LineService{
	
	@Autowired
	private LineDAO lineDAO ;

	@Override
	public Line get(Class<Line> entityClazz, Serializable id) {
		return lineDAO.get(entityClazz, id) ;
	}

	@Override
	public Serializable save(Line entity) {
		return lineDAO.save(entity) ;
	}

	@Override
	public void update(Line entity) {
		lineDAO.update(entity);
	}

	@Override
	public void delete(Line entity) {
		lineDAO.delete(entity);
	}

	@Override
	public void delete(Class<Line> entityClazz, Serializable id) {
		lineDAO.delete(entityClazz, id);
	}

	@Override
	public List<Line> findAll(Class<Line> entityClazz) {
		return lineDAO.findAll(entityClazz) ;
	}

	@Override
	public long findCount(Class<Line> entityClazz) {
		return lineDAO.findCount(entityClazz) ;
	}

	public LineDAO getLineDAO() {
		return lineDAO;
	}

	public void setLineDAO(LineDAO lineDAO) {
		this.lineDAO = lineDAO;
	}

}
