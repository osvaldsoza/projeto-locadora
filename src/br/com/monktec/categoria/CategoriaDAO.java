package br.com.monktec.categoria;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.monktec.util.HibernateUtil;

public class CategoriaDAO {

	private Session session;
	private Transaction transction;

	public void salvar(Categoria categoria) {
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.transction = this.session.beginTransaction();
			this.session.save(categoria);
			this.transction.commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possível inserir a categoria. Error: " + e.getMessage());
		} finally {
			try {
				if (this.session.isOpen()) {
					this.session.close();
				}
			} catch (Throwable e) {
				System.out.println("Error ao fechar operação de inserção. Mensagem: " + e.getMessage());
			}
		}
	}

	public void atualizar(Categoria categoria) {
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.transction = this.session.beginTransaction();
			this.session.update(categoria);
			this.transction.commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possível atualizar a categoria. Error: " + e.getMessage());
		} finally {
			try {
				if (this.session.isOpen()) {
					this.session.close();
				}
			} catch (Throwable e) {
				System.out.println("Error ao fechar operação de atualização. Mensagem: " + e.getMessage());
			}
		}
	}

	public void excluir(Categoria categoria) {
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.transction = this.session.beginTransaction();
			this.session.delete(categoria);
			this.transction.commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possível excluir a categoria. Error: " + e.getMessage());
		} finally {
			try {
				if (this.session.isOpen()) {
					this.session.close();
				}
			} catch (Throwable e) {
				System.out.println("Error ao fechar operação de exclusão. Menzagem: " + e.getMessage());
			}
		}
	}
	
	public Categoria buscarCategoria(Integer codigo){
		Categoria categoria = null;
		
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.transction = this.session.beginTransaction();
					
			@SuppressWarnings("deprecation")
			Criteria criterioDaBusca = this.session.createCriteria(Categoria.class);
			criterioDaBusca.add(Restrictions.eq("categoria", codigo));
			
			categoria = (Categoria) criterioDaBusca.uniqueResult();

			this.transction.commit();
		} catch (Throwable e) {
			if(this.transction.isActive()){
				this.transction.rollback();
			}
		}finally{
			try {
				if(this.session.isOpen()){
					this.session.close();
				}
			} catch (Throwable e) {
				System.out.println("Error ao fechar operação de busca por código. Mensagem: " + e.getMessage());
			}
		}
		return categoria;
	}
	
	@SuppressWarnings("unchecked")
	public List<Categoria> listar(){
		List<Categoria> categorias = null;
		
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.transction = this.session.beginTransaction();
			
			@SuppressWarnings("deprecation")
			Criteria criterioDaBusca = this.session.createCriteria(Categoria.class);
			categorias = criterioDaBusca.list();
			
			this.transction.commit();
		} catch (Throwable e) {
			if(this.transction.isActive()){
				this.transction.rollback();
			}
		}finally{
			try {
				if(this.session.isOpen()){
					this.session.close();
				}
			} catch (Throwable e) {
				System.out.println("Error ao fechar operação de busca por código. Mensagem: " + e.getMessage());
			}
		}
		return categorias;
	}
}
