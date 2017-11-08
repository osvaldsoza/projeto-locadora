package br.com.monktec.util;

import org.hibernate.Session;

public class TestarConexao {

	public static void main(String[] args) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			System.out.println("conectado");
		} finally {
			session.close();
		}
	}

}
