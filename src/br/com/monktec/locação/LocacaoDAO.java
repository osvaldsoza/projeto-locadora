package br.com.monktec.locação;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.monktec.util.HibernateUtil;

public class LocacaoDAO {

	private Session session;
	private Transaction transaction;

	public void salvar(Locacao locacao) {
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.transaction = this.session.beginTransaction();
			this.session.save(locacao);
			this.transaction.commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possível incluir a locação. Error: " + e.getMessage());
		} finally {
			try {
				if (this.session.isOpen()) {
					this.session.close();
				}
			} catch (Throwable e) {
				System.out.println("Error ao fechar operação de inclusão. Mensagem: " + e.getMessage());
			}
		}
	}

	public void atualizar(Locacao locacao) {
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.transaction = this.session.beginTransaction();
			this.session.update(locacao);
			this.transaction.commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possível atulizar a locação. Error: " + e.getMessage());
		} finally {
			try {
				if (this.session.isOpen()) {
					this.session.close();
				}
			} catch (Throwable e) {
				System.out.println("Error ao fechar operação de atualizar. Mensagem: " + e.getMessage());
			}
		}
	}

	public void excluir(Locacao locacao) {
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.transaction = this.session.beginTransaction();
			this.session.delete(locacao);
			this.transaction.commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possível deletar a locação. Error: " + e.getMessage());
		} finally {
			try {
				if (this.session.isOpen()) {
					this.session.close();
				}
			} catch (Throwable e) {
				System.out.println("Error ao fechar operação de excluir. Mensagem: " + e.getMessage());
			}
		}
	}
	
	public Locacao buscarLocacao(Integer codigo){
		Locacao locacao = null;
		
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.transaction = this.session.beginTransaction();
			
			Criteria criterioDeBusca = this.session.createCriteria(Locacao.class);
			criterioDeBusca.add(Restrictions.eq("locacao", codigo));
			
			locacao = (Locacao) criterioDeBusca.uniqueResult();

			this.transaction.commit();
			
		} catch (Throwable e) {
			if(this.transaction.isActive()){
				this.transaction.rollback();
			}
		}finally{
			try {
				if(this.session.isOpen()){
					this.session.close();
				}
			} catch (Throwable e) {
				System.out.println("Error ao fechar operação de buscar por código. Mensagem: " + e.getMessage());
			}
		}
		return locacao;
	}
	
	public List<Locacao> listarLocacoes(){
		List<Locacao> locacoes = null;
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.transaction = this.session.beginTransaction();
			
			Criteria criterioDeBusca = this.session.createCriteria(Locacao.class);
			criterioDeBusca.list();
			
			this.transaction.commit();			
		} catch (Throwable e) {
			if(this.transaction.isActive()){
				this.transaction.rollback();
			}
		}finally{
			try {
				
			} catch (Throwable e) {
				// TODO: handle exception
			}
		}
		return locacoes;
	}
	
	

}
