package br.com.caelum.ingresso.model;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IngressoTest {
	
	private Filme clubeDaLuta;
	private Sala salaNormal;
	private Sessao sessao;
	private Lugar lugar;

	@Before
	public void preparaSessao() {
		clubeDaLuta = new Filme("Clube da Luta", Duration.ofHours(2), "Ação", new BigDecimal("13.00"));
		salaNormal = new Sala("Sala Normal", new BigDecimal("17.00"));
		
		sessao = new Sessao(LocalTime.now(), clubeDaLuta, salaNormal);
		lugar = new Lugar("A", 1);
	}

	@Test
	public void deveAplicarDescontoDeCinquentaPorCentoQuandoForParaEstudante() {
		// ARRANJAR o cenário
		Ingresso ingressoParaEstudante = new Ingresso(sessao, TipoDeIngresso.ESTUDANTE, lugar);

		// AGIR na funcionalidade
		BigDecimal precoDoIngresso = ingressoParaEstudante.getPreco();
		
		// ASSEGURAR os resultados esperados
		BigDecimal quinzeReais = new BigDecimal("15.00");
		Assert.assertEquals(quinzeReais, precoDoIngresso);
	}
	
	@Test
	public void deveAplicarDescontoDeTrintaPorCentoQuandoForParaBancario() {
		// ARRANJAR o cenário
		Ingresso ingressoParaEstudante = new Ingresso(sessao, TipoDeIngresso.BANCARIO, lugar);

		// AGIR na funcionalidade
		BigDecimal precoDoIngresso = ingressoParaEstudante.getPreco();
		
		// ASSEGURAR os resultados esperados
		BigDecimal vinteUmReais = new BigDecimal("21.00");
		Assert.assertEquals(vinteUmReais, precoDoIngresso);
	}
	
	@Test
	public void naoDeveAplicarDescontoQuantoNaoTiverDesconto() {
		// ARRANJAR o cenário
		Ingresso ingressoParaEstudante = new Ingresso(sessao, TipoDeIngresso.INTEIRO, lugar);

		// AGIR na funcionalidade
		BigDecimal precoDoIngresso = ingressoParaEstudante.getPreco();
		
		// ASSEGURAR os resultados esperados
		BigDecimal vinteUmReais = new BigDecimal("30.00");
		Assert.assertEquals(vinteUmReais, precoDoIngresso);
	}
}
