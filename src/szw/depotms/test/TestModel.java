package szw.depotms.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class TestModel {
	@SuppressWarnings("deprecation")
	public static void main(String args[]) {
		Configuration cfg = new Configuration() ;
		SessionFactory sf = cfg.configure().buildSessionFactory() ;		//½âÎöhibernate.cfg.xml
		Session session = sf.openSession() ;
		session.beginTransaction() ;
		
		session.getTransaction().commit(); 
		session.close() ;
		sf.close() ;
	}
}
