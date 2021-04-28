package br.com.caelum.ingresso.validacao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import br.com.caelum.ingresso.model.Sessao;

public class GerenciadorDeSessao {
	
	private List<Sessao> sessoesExistentes;
	
	public GerenciadorDeSessao(List<Sessao> sessoesExistentes) {
		this.sessoesExistentes = sessoesExistentes;
	}

	
	public boolean cabe(Sessao sessao) {
		if (terminaAmanha(sessao)) {
			return false;
		}
		
		/*
		for (Sessao sessaoExistente : sessoesExistentes) {
			if (horarioConflita(sessao, sessaoExistente)) {
				return false;
			}
		}
		*/
		
		return sessoesExistentes.stream()
			.noneMatch(sessaoExistente -> horarioConflita(sessao, sessaoExistente));
	}
	
	private boolean horarioConflita(Sessao nova, Sessao existente) {
		LocalDateTime terminoDaSessaoNova = getTerminoDaSessao(nova);
		LocalDateTime inicioDaSessaoExistente = getInicioDaSessaoNoDiaHoje(existente);
		
		boolean terminaAntes = terminoDaSessaoNova.isBefore(inicioDaSessaoExistente);
		
		LocalDateTime inicioDaSessaoNova = getInicioDaSessaoNoDiaHoje(nova);
		LocalDateTime terminoDaSessaoExistente = getTerminoDaSessao(existente);
		
		boolean comecaDepois = inicioDaSessaoNova.isAfter(terminoDaSessaoExistente);
		
		if (terminaAntes || comecaDepois) {
			return false;
		}
		
		return true;
	}
	
	private boolean terminaAmanha(Sessao sessao) {
		LocalDateTime termino = getTerminoDaSessao(sessao);
		
		LocalDateTime ultimoSegundoDeHoje = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
		
		return termino.isAfter(ultimoSegundoDeHoje);
	}
	
	
	private LocalDateTime getTerminoDaSessao(Sessao sessao) {
		LocalDateTime inicio = getInicioDaSessaoNoDiaHoje(sessao);
		LocalDateTime termino = inicio.plus(sessao.getFilme().getDuracao());
		
		return termino;
	}
	
	private LocalDateTime getInicioDaSessaoNoDiaHoje(Sessao sessao) {
		return sessao.getHorario().atDate(LocalDate.now());
	}
	

}


















