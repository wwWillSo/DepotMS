package szw.depotms.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import szw.depotms.dao.CarDAO;
import szw.depotms.model.Car;
import szw.depotms.service.CarService;
@Transactional
public class CarServiceImpl implements CarService{
	
	@Autowired
	private CarDAO carDAO ;

	@Override
	public Car get(Class<Car> entityClazz, Serializable id) {
		return carDAO.get(entityClazz, id) ;
	}

	@Override
	public Serializable save(Car entity) {
		return carDAO.save(entity) ;
	}

	@Override
	public void update(Car entity) {
		carDAO.update(entity);
	}

	@Override
	public void delete(Car entity) {
		carDAO.delete(entity);
	}

	@Override
	public void delete(Class<Car> entityClazz, Serializable id) {
		carDAO.delete(entityClazz, id);
	}

	@Override
	public List<Car> findAll(Class<Car> entityClazz) {
		return carDAO.findAll(entityClazz) ;
	}

	@Override
	public long findCount(Class<Car> entityClazz) {
		return carDAO.findCount(entityClazz) ;
	}

	public CarDAO getCarDAO() {
		return carDAO;
	}

	public void setCarDAO(CarDAO carDAO) {
		this.carDAO = carDAO;
	}
}
