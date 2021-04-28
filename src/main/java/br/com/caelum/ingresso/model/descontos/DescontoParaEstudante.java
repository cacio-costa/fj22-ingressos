package br.com.caelum.ingresso.model.descontos;

import java.math.BigDecimal;

public class DescontoParaEstudante implements Desconto {

	public BigDecimal aplicaDescontoSobre(BigDecimal precoOriginal) {
		return precoOriginal.multiply(new BigDecimal("0.5"));
	}
	
}
