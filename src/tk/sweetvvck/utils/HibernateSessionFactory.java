package tk.sweetvvck.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactory {
	private static SessionFactory sessionFactory = null;
	
	private HibernateSessionFactory(){}
	
	static {
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}
	
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
}
