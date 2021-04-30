package br.com.caelum.ingresso.model.descontos;

import java.math.BigDecimal;

public class DescontoParaBancos implements Desconto {

	public BigDecimal aplicaDescontoSobre(BigDecimal precoOriginal) {
		return precoOriginal.multiply(new BigDecimal("0.7"));
	}
	
	public String getDescricao() {
		return "Bancário";
	}
}
