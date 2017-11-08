package br.com.monktec.midia;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.monktec.util.HibernateUtil;

public class MidiaDAO {

	private Session session;
	private Transaction transaction;

	public void salvar(Midia midia) {
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.transaction = this.session.beginTransaction();
			this.session.save(midia);
			this.transaction.commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possível incluir a midia. Error: " + e.getMessage());
		}finally{
			try {
				if(this.session.isOpen()){
					this.session.close();
				}
			} catch (Throwable e) {
				System.out.println("Error ao fechar operação de inclusão. Mensagem: " + e.getMessage());
			}
		}
	}
	
	public void atualizar(Midia midia){
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.transaction = this.session.beginTransaction();
			this.session.update(midia);
			this.transaction.commit();			
		} catch (HibernateException e) {
			System.out.println("Não foi possível atualizar a midia. Error: " + e.getMessage());
		}finally{
			try {
				if(this.session.isOpen()){
					this.session.close();
				}
			} catch (Throwable e) {
				System.out.println("Error ao fechar operação de inclusão. Mensagem: " + e.getMessage());
			}
		}
	}
	
	public void excluir(Midia midia){
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.transaction = this.session.beginTransaction();
			this.session.delete(midia);
			this.transaction.commit();			
		} catch (HibernateException e) {
			System.out.println("Não foi possível excluir a midia. Error: " + e.getMessage());
		}finally{
			try {
				if(this.session.isOpen()){
					this.session.close();
				}
			} catch (Throwable e) {
				System.out.println("Error ao fechar operação de exclusão. Mensagem: " + e.getMessage());
			}
		}
	}
	
	public Midia bucarMidia(Integer codigo){
		Midia midia = null;		
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.transaction = this.session.beginTransaction();
			
			Criteria criterioDeBusca = this.session.createCriteria(Midia.class);
			criterioDeBusca.add(Restrictions.eq("midia", codigo));
			midia = (Midia) criterioDeBusca.uniqueResult();
			
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
				System.out.println("Error ao fechar operação de busca. Mensagem: " + e.getMessage());
			}
		}
				
		return midia;
	}
	
	public List<Midia> listarMidias(){
		List<Midia> midias = null;		
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.transaction = this.session.beginTransaction();
			
			Criteria criterioDeBusca = this.session.createCriteria(Midia.class);
			criterioDeBusca.list();
			
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
				System.out.println("Error ao fechar operação de listagem. Mensagem: " + e.getMessage());
			}
		}
		return midias;
	}
	

}
