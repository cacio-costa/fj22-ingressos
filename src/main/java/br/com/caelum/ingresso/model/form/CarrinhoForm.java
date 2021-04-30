package br.com.caelum.ingresso.model.form;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.caelum.ingresso.dao.LugarDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.Lugar;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.TipoDeIngresso;

public class CarrinhoForm {

	private List<Ingresso> ingressos = new ArrayList<>();

	public List<Ingresso> getIngressos() {
		return ingressos;
	}

	public void setIngressos(List<Ingresso> ingressos) {
		this.ingressos = ingressos;
	}

	public List<Ingresso> toIngressos(SessaoDao sessaoDao, LugarDao lugarDao) {
		/*
		List<Ingresso> ingressosCompletos = new ArrayList<>();
		
		for (Ingresso ingressoIncompleto : ingressos) {
			TipoDeIngresso tipoDeIngresso = ingressoIncompleto.getTipoDeIngresso();
			Sessao sessaoCompleta = sessaoDao.findOne(ingressoIncompleto.getSessao().getId());
			Lugar lugarCompleto = lugarDao.findOne(ingressoIncompleto.getLugar().getId());
			
			Ingresso ingressoCompleto = new Ingresso(sessaoCompleta, tipoDeIngresso, lugarCompleto);
			ingressosCompletos.add(ingressoCompleto);
		}
		
		return ingressosCompletos;
		*/
		
		return ingressos.stream()
			.map(ingressoIncompleto -> {
				TipoDeIngresso tipoDeIngresso = ingressoIncompleto.getTipoDeIngresso();
				Sessao sessaoCompleta = sessaoDao.findOne(ingressoIncompleto.getSessao().getId());
				Lugar lugarCompleto = lugarDao.findOne(ingressoIncompleto.getLugar().getId());
				
				return new Ingresso(sessaoCompleta, tipoDeIngresso, lugarCompleto);
			})
			.collect(Collectors.toList());
	}
}
