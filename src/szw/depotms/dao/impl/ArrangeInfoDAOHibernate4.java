package szw.depotms.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import szw.depotms.dao.ArrangeInfoDAO;
import szw.depotms.model.ArrangeInfo;

public class ArrangeInfoDAOHibernate4 implements ArrangeInfoDAO{
	
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
	public ArrangeInfo get(Class<ArrangeInfo> entityClazz, Serializable id) {
		return (ArrangeInfo)getSessionFactory().getCurrentSession().get(entityClazz, id) ;
	}

	@Override
	public Serializable save(ArrangeInfo entity) {
		return getSessionFactory().getCurrentSession().save(entity) ;
	}

	@Override
	public void update(ArrangeInfo entity) {
		getSessionFactory().getCurrentSession().saveOrUpdate(entity);
	}

	@Override
	public void delete(ArrangeInfo entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}

	@Override
	public void delete(Class<ArrangeInfo> entityClazz, Serializable id) {
		getSessionFactory().getCurrentSession()
			.createQuery("delete " + entityClazz.getSimpleName() + " en where en.id = ?0")
			.setParameter("0", id)
			.executeUpdate() ;
	}

	@Override
	public List<ArrangeInfo> findAll(Class<ArrangeInfo> entityClazz) {
		return find("select en from " + entityClazz.getSimpleName() + " en") ;
	}

	@Override
	public long findCount(Class<ArrangeInfo> entityClazz) {
		List<?> list = find ("select count(*) from " + entityClazz.getSimpleName()) ;
		//���ز�ѯ�õ���ʵ������
		if (list!=null && list.size() == 1) {
			return (long)list.get(0) ;
		}
		
		return 0 ; 
	}
	
	//����HQL��ѯʵ��
	@SuppressWarnings("unchecked")
	public List<ArrangeInfo> find(String hql) {
		return (List<ArrangeInfo>)getSessionFactory().getCurrentSession().createQuery(hql).list() ;
	}
	
	//���ݴ�ռλ��������HQL��ѯʵ��
	@SuppressWarnings("unchecked")
	public List<ArrangeInfo> find(String hql, Object[] params) {
		//������ѯ
		Query query = getSessionFactory().getCurrentSession().createQuery(hql) ;
		//Ϊ����ռλ����HQL������ò���
		for (int i = 0, len=params.length; i < len; i ++) {
			query.setParameter(i + "", params[i]) ;
		}
		return (List<ArrangeInfo>)query.list() ;
	}
	
	/**
	 * ��ҳ��ѯ
	 */
	@SuppressWarnings("unchecked")
	public List<ArrangeInfo> findByPage(String hql, int pageNo, int pageSize) {
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
	public List<ArrangeInfo> findByPage(String hql, int pageNo, int pageSize, Object[] params) {
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
