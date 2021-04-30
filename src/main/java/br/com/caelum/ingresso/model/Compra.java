package br.com.caelum.ingresso.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Compra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToMany(cascade = CascadeType.PERSIST)
	private List<Ingresso> ingressos = new ArrayList<>();

	@Embedded
	private Cartao cartao;
	
	/**
	 * @deprecated
	 */
	public Compra() {
		
	}
	
	public Compra(List<Ingresso> ingressosPagos, Cartao cartao) {
		this.cartao = cartao;
		ingressosPagos.forEach(this.ingressos::add);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Ingresso> getIngressos() {
		return ingressos;
	}

	public void setIngressos(List<Ingresso> ingressos) {
		this.ingressos = ingressos;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}

}
