package szw.depotms.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import szw.depotms.dao.DriverDAO;
import szw.depotms.model.Driver;

public class DriverDAOHibernate4 implements DriverDAO{
	
	//DAO�齨���г־û������ײ�������SessionFactory���
	@Autowired
	private SessionFactory sessionFactory ;
	
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf ;
	}
	public SessionFactory getSessionFactory() {
		return this.sessionFactory ;
	}

	@Override
	public Driver get(Class<Driver> entityClazz, Serializable id) {
		return (Driver)getSessionFactory().getCurrentSession().get(entityClazz, id) ;
	}

	@Override
	public Serializable save(Driver entity) {
		return getSessionFactory().getCurrentSession().save(entity) ;
	}

	@Override
	public void update(Driver entity) {
		getSessionFactory().getCurrentSession().saveOrUpdate(entity);
	}

	@Override
	public void delete(Driver entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}

	@Override
	public void delete(Class<Driver> entityClazz, Serializable id) {
		getSessionFactory().getCurrentSession()
			.createQuery("delete " + entityClazz.getSimpleName() + " en where en.id = ?0")
			.setParameter("0", id)
			.executeUpdate() ;
	}

	@Override
	public List<Driver> findAll(Class<Driver> entityClazz) {
		return find("select en from " + entityClazz.getSimpleName() + " en") ;
	}

	@Override
	public long findCount(Class<Driver> entityClazz) {
		List<?> list = find ("select count(*) from " + entityClazz.getSimpleName()) ;
		//���ز�ѯ�õ���ʵ������
		if (list!=null && list.size() == 1) {
			return (long)list.get(0) ;
		}
		
		return 0 ; 
	}
	
	//����HQL��ѯʵ��
	@SuppressWarnings("unchecked")
	public List<Driver> find(String hql) {
		return (List<Driver>)getSessionFactory().getCurrentSession().createQuery(hql).list() ;
	}
	
	//���ݴ�ռλ��������HQL��ѯʵ��
	@SuppressWarnings("unchecked")
	public List<Driver> find(String hql, Object[] params) {
		//������ѯ
		Query query = getSessionFactory().getCurrentSession().createQuery(hql) ;
		//Ϊ����ռλ����HQL������ò���
		for (int i = 0, len=params.length; i < len; i ++) {
			query.setParameter(i + "", params[i]) ;
		}
		return (List<Driver>)query.list() ;
	}
	
	/**
	 * ��ҳ��ѯ
	 */
	@SuppressWarnings("unchecked")
	public List<Driver> findByPage(String hql, int pageNo, int pageSize) {
		//������ѯ
		return getSessionFactory().getCurrentSession().createQuery(hql)
				.setFirstResult((pageNo-1) * pageSize)
				.setMaxResults(pageSize)
				.list() ;
	}
	
	/**
	 * ��ռλ�������ķ�ҳ��ѯ
	 */
	@SuppressWarnings("unchecked")
	public List<Driver> findByPage(String hql, int pageNo, int pageSize, Object[] params) {
		//������ѯ
		Query query = getSessionFactory().getCurrentSession().createQuery(hql) ;
		
		//Ϊ����ռλ����hql������ò���
		for (int i = 0, len = params.length; i < len; i ++) {
			query.setParameter(i + "", params[i]) ;
		}
		
		//ִ�з�ҳ�������ز�ѯ���
		return query.setFirstResult((pageNo-1)*pageSize)
				.setMaxResults(pageSize)
				.list() ;
	}
}
