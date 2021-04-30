package br.com.caelum.ingresso.model;

import java.time.YearMonth;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Embeddable
public class Cartao {

	@NotNull @NotBlank // Validações do Bean Validation
	private String cartaoDeCredito;
	
	@NotNull
	private Integer cvv;
	
	@NotNull
	private YearMonth vencimento;
	
	
	public String getCartaoDeCredito() {
		return cartaoDeCredito;
	}
	
	public void setCartaoDeCredito(String cartaoDeCredito) {
		this.cartaoDeCredito = cartaoDeCredito;
	}
	
	public Integer getCvv() {
		return cvv;
	}
	
	public void setCvv(Integer cvv) {
		this.cvv = cvv;
	}
	
	public YearMonth getVencimento() {
		return vencimento;
	}
	
	public void setVencimento(YearMonth vencimento) {
		this.vencimento = vencimento;
	}
	
	public boolean isVencido() {
		return vencimento.isBefore(YearMonth.now());
	}
	
}
