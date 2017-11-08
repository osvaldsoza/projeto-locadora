package br.com.monktec.filme;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.monktec.util.HibernateUtil;

public class FilmeDAO {
	private Session session;
	private Transaction transaction;

	public void salvar(Filme filme) {
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.transaction = this.session.beginTransaction();
			this.session.save(filme);
			this.transaction.commit();
		} catch (HibernateException e) {
			System.out.println("N�o foi poss�vel inserir  o  filme. Error: " + e.getMessage());
		} finally {
			try {
				if (this.session.isOpen()) {
					this.session.close();
				}
			} catch (Throwable e) {
				System.out.println("Error ao fechar opera��o de inser��o. Mensagem: " + e.getMessage());
			}
		}
	}

	public void atualizar(Filme filme) {
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.transaction = this.session.beginTransaction();
			this.session.update(filme);
			this.transaction.commit();
		} catch (HibernateException e) {
			System.out.println("N�o foi poss�vel atualizar  o  filme. Error: " + e.getMessage());
		} finally {
			try {
				if (this.session.isOpen()) {
					this.session.close();
				}
			} catch (Throwable e) {
				System.out.println("Error ao fechar opera��o de atualiza��o. Mensagem: " + e.getMessage());
			}
		}
	}

	public void excluir(Filme filme) {
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.transaction = this.session.beginTransaction();
			this.session.delete(filme);
			this.transaction.commit();
		} catch (HibernateException e) {
			System.out.println("N�o foi poss�vel excluir o  filme. Error: " + e.getMessage());
		} finally {
			try {
				if (this.session.isOpen()) {
					this.session.close();
				}
			} catch (Throwable e) {
				System.out.println("Error ao fechar opera��o de exclus�o. Mensagem: " + e.getMessage());
			}
		}
	}
	
	public Filme buscaFilme(Integer codigo){
		Filme filme = null;
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.transaction = this.session.beginTransaction();
			
			Criteria criterioDaBusca = this.session.createCriteria(Filme.class);
			criterioDaBusca.add(Restrictions.eq("filme", codigo));
			filme = (Filme) criterioDaBusca.uniqueResult();
			
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
				System.out.println("Error ao fechar opera��o de busca. Mensagem: " + e.getMessage());
			}
		}
		return filme;
	}
	
	public List<Filme> listarFilmes(){
		List<Filme> filmes = null;
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.transaction = this.session.beginTransaction();
			
			Criteria criterioDaBusca = this.session.createCriteria(Filme.class);
			criterioDaBusca.list();
			
			this.transaction.commit();
		} catch (Throwable e) {
			if(this.transaction.isActive()){
				this.transaction.rollback();
			}
		}finally{
			try {
				
			} catch (Throwable e) {
				System.out.println("Error ao fechar opera��o de listar filmes. Mensagem: " + e.getMessage());
			}
		}
		return filmes;
	}

}
