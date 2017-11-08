package br.com.monktec.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static SessionFactory sessionFactory = getBuildSessionFactory();

	private static SessionFactory getBuildSessionFactory() {
		try {
			Configuration cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");
			return cfg.buildSessionFactory();			
		} catch (Throwable e) {
			System.err.println("Error ao tentar criar sessionFactory. Mensagem: " + e.getMessage());
			throw new ExceptionInInitializerError();
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}	
}
