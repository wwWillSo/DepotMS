package szw.depotms.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import szw.depotms.dao.MonthArrangeDAO;
import szw.depotms.model.MonthArrange;

public class MonthArrangeDAOHibernate4 implements MonthArrangeDAO{
	
	//DAO组建进行持久化操作底层以来的SessionFactory组件
	@Autowired
	private SessionFactory sessionFactory ;
	
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf ;
	}
	public SessionFactory getSessionFactory() {
		return this.sessionFactory ;
	}

	@Override
	public MonthArrange get(Class<MonthArrange> entityClazz, Serializable id) {
		return (MonthArrange)getSessionFactory().getCurrentSession().get(entityClazz, id) ;
	}

	@Override
	public Serializable save(MonthArrange entity) {
		return getSessionFactory().getCurrentSession().save(entity) ;
	}

	@Override
	public void update(MonthArrange entity) {
		getSessionFactory().getCurrentSession().saveOrUpdate(entity);
	}

	@Override
	public void delete(MonthArrange entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}

	@Override
	public void delete(Class<MonthArrange> entityClazz, Serializable id) {
		getSessionFactory().getCurrentSession()
			.createQuery("delete " + entityClazz.getSimpleName() + " en where en.id = ?0")
			.setParameter("0", id)
			.executeUpdate() ;
	}

	@Override
	public List<MonthArrange> findAll(Class<MonthArrange> entityClazz) {
		return find("select en from " + entityClazz.getSimpleName() + " en") ;
	}

	@Override
	public long findCount(Class<MonthArrange> entityClazz) {
		List<?> list = find ("select count(*) from " + entityClazz.getSimpleName()) ;
		//返回查询得到的实体总数
		if (list!=null && list.size() == 1) {
			return (long)list.get(0) ;
		}
		
		return 0 ; 
	}
	
	//根据HQL查询实体
	@SuppressWarnings("unchecked")
	public List<MonthArrange> find(String hql) {
		return (List<MonthArrange>)getSessionFactory().getCurrentSession().createQuery(hql).list() ;
	}
	
	//根据带占位符参数的HQL查询实体
	@SuppressWarnings("unchecked")
	public List<MonthArrange> find(String hql, Object[] params) {
		//创建查询
		Query query = getSessionFactory().getCurrentSession().createQuery(hql) ;
		//为包含占位符的HQL语句设置参数
		for (int i = 0, len=params.length; i < len; i ++) {
			query.setParameter(i + "", params[i]) ;
		}
		return (List<MonthArrange>)query.list() ;
	}
	
	/**
	 * 分页查询
	 */
	@SuppressWarnings("unchecked")
	public List<MonthArrange> findByPage(String hql, int pageNo, int pageSize) {
		//创建查询
		return getSessionFactory().getCurrentSession().createQuery(hql)
				.setFirstResult((pageNo-1) * pageSize)
				.setMaxResults(pageSize)
				.list() ;
	}
	
	/**
	 * 带占位符参数的分页查询
	 */
	@SuppressWarnings("unchecked")
	public List<MonthArrange> findByPage(String hql, int pageNo, int pageSize, Object[] params) {
		//创建查询
		Query query = getSessionFactory().getCurrentSession().createQuery(hql) ;
		
		//为包含占位符的hql语句设置参数
		for (int i = 0, len = params.length; i < len; i ++) {
			query.setParameter(i + "", params[i]) ;
		}
		
		//执行分页，并返回查询结果
		return query.setFirstResult((pageNo-1)*pageSize)
				.setMaxResults(pageSize)
				.list() ;
	}
}
