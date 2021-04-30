package br.com.caelum.ingresso.model;

import java.math.BigDecimal;

import br.com.caelum.ingresso.model.descontos.Desconto;
import br.com.caelum.ingresso.model.descontos.DescontoParaBancos;
import br.com.caelum.ingresso.model.descontos.DescontoParaEstudante;
import br.com.caelum.ingresso.model.descontos.SemDesconto;

public enum TipoDeIngresso {

	BANCARIO(new DescontoParaBancos()),
	ESTUDANTE(new DescontoParaEstudante()),
	INTEIRO(new SemDesconto());

	private Desconto desconto;
	
	private TipoDeIngresso(Desconto desconto) {
		this.desconto = desconto;
	}
	
	public String getDescricao() {
		return this.desconto.getDescricao();
	}
	
	public BigDecimal aplicaDescontoSobre(BigDecimal precoOriginal) {
		return this.desconto.aplicaDescontoSobre(precoOriginal);
	}
	
}
