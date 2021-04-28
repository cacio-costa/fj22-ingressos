package br.com.caelum.ingresso.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.caelum.ingresso.model.descontos.Desconto;

@Entity
public class Ingresso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private BigDecimal preco = BigDecimal.ZERO;

	@ManyToOne
	private Sessao sessao;
	
	/**
	 * JPA (Hibernate) precisam do construtor padrão.
	 * @deprecated
	 */
	public Ingresso() {
		
	}
	
	// SOLID: Open para extensão, Close para alteração: Open-Close Principle
	public Ingresso(Sessao sessao, Desconto desconto) {
		this.sessao = sessao;
		this.preco = desconto.aplicaDescontoSobre(sessao.getPreco());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getPreco() {
		return preco.setScale(2, RoundingMode.HALF_UP);
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Sessao getSessao() {
		return sessao;
	}

	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}

}
