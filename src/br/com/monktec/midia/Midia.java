package br.com.monktec.midia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.monktec.filme.Filme;

@Entity
@Table(name = "midia")
public class Midia implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="cod_midia")
	private Integer midia;
	
	@ManyToOne
	@JoinColumn(name="cod_filme")
	private Filme filme;
	
	private String initializada;
	
	public Integer getMidia() {
		return midia;
	}
	public void setMidia(Integer midia) {
		this.midia = midia;
	}
	public Filme getFilme() {
		return filme;
	}
	public void setFilme(Filme filme) {
		this.filme = filme;
	}
	public String getInitializada() {
		return initializada;
	}
	public void setInitializada(String initializada) {
		this.initializada = initializada;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((filme == null) ? 0 : filme.hashCode());
		result = prime * result + ((initializada == null) ? 0 : initializada.hashCode());
		result = prime * result + ((midia == null) ? 0 : midia.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Midia other = (Midia) obj;
		if (filme == null) {
			if (other.filme != null)
				return false;
		} else if (!filme.equals(other.filme))
			return false;
		if (initializada == null) {
			if (other.initializada != null)
				return false;
		} else if (!initializada.equals(other.initializada))
			return false;
		if (midia == null) {
			if (other.midia != null)
				return false;
		} else if (!midia.equals(other.midia))
			return false;
		return true;
	}
}
