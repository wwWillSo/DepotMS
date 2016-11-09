package szw.depotms.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import szw.depotms.dao.ClassesDAO;
import szw.depotms.model.Classes;
import szw.depotms.service.ClassesService;
@Transactional
public class ClassesServiceImpl implements ClassesService{
	
	@Autowired
	private ClassesDAO classesDAO ;

	@Override
	public Classes get(Class<Classes> entityClazz, Serializable id) {
		return classesDAO.get(entityClazz, id) ;
	}

	@Override
	public Serializable save(Classes entity) {
		return classesDAO.save(entity) ;
	}

	@Override
	public void update(Classes entity) {
		classesDAO.update(entity);
	}

	@Override
	public void delete(Classes entity) {
		classesDAO.delete(entity);
	}

	@Override
	public void delete(Class<Classes> entityClazz, Serializable id) {
		classesDAO.delete(entityClazz, id);
	}

	@Override
	public List<Classes> findAll(Class<Classes> entityClazz) {
		return classesDAO.findAll(entityClazz) ;
	}

	@Override
	public long findCount(Class<Classes> entityClazz) {
		return classesDAO.findCount(entityClazz) ;
	}

	public ClassesDAO getClassesDAO() {
		return classesDAO;
	}

	public void setClassesDAO(ClassesDAO classesDAO) {
		this.classesDAO = classesDAO;
	}

}
