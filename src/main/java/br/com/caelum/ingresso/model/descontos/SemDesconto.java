package br.com.caelum.ingresso.model.descontos;

import java.math.BigDecimal;

public class SemDesconto implements Desconto {

	@Override
	public BigDecimal aplicaDescontoSobre(BigDecimal precoOrinal) {
		return precoOrinal;
	}

	public String getDescricao() {
		return "Inteiro";
	}
}
