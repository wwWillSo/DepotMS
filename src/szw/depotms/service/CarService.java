package szw.depotms.service;

import java.io.Serializable;
import java.util.List;

import szw.depotms.model.Car;

public interface CarService {
	public Car get(Class<Car> entityClazz, Serializable id) ;
	
	public Serializable save(Car entity) ;
	
	public void update(Car entity) ;
	
	public void delete(Car entity) ;
	
	public void delete(Class<Car> entityClazz, Serializable id) ;
	
	public List<Car> findAll(Class<Car> entityClazz) ;
	
	public long findCount(Class<Car> entityClazz) ;
}
