package br.com.caelum.ingresso.model;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Test;

public class SessaoTest {

	@Test
	public void validarPrecoDaSessao() {
		// ARRANJAR
		Filme reiLeao = new Filme("O Rei Leão", Duration.ofHours(2), "Animação", BigDecimal.TEN);
		Sala sala = new Sala("Sala Normal", BigDecimal.TEN);
		
		Sessao sessao = new Sessao(LocalTime.now(), reiLeao, sala);
		
		// AGIR
		BigDecimal precoFinal = sessao.getPreco();
				
		// ASSEGURAR
		BigDecimal vinteReais = new BigDecimal("20.00"); 
		Assert.assertEquals(vinteReais, precoFinal);
	}
	
}
