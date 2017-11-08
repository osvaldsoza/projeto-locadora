package br.com.monktec.cliente;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.monktec.util.HibernateUtil;

public class ClienteDAO {

	private Session session;
	private Transaction transaction;

	public void salvar(Cliente cliente) {
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.transaction = this.session.beginTransaction();
			this.session.save(cliente);
			this.transaction.commit();
		} catch (HibernateException e) {
			System.out.println("N�o foi poss�vel salvar a loca��o. Error: " + e.getMessage());
		} finally {
			try {
				if (this.session.isOpen()) {
					this.session.close();
				}
			} catch (Throwable e) {
				System.out.println("Error ao fechar opera��o de salvar. Mensagem: " + e.getMessage());
			}
		}
	}

	public void atualizar(Cliente cliente) {
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.transaction = this.session.beginTransaction();
			this.session.update(cliente);
			this.transaction.commit();
		} catch (HibernateException e) {
			System.out.println("N�o foi poss�vel atualizar a loca��o. Error: " + e.getMessage());
		} finally {
			try {
				if (this.session.isOpen()) {
					this.session.close();
				}
			} catch (Throwable e) {
				System.out.println("Error ao fechar opera��o de atualizar. Mensagem: " + e.getMessage());
			}
		}
	}
}
