package br.com.caelum.ingresso.model.form;

import java.time.LocalTime;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SalaDao;
import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;

public class SessaoForm {

	@NotNull
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime horario;
	
	@NotNull
	private Integer salaId;
	
	@NotNull
	private Integer filmeId;

	public LocalTime getHorario() {
		return horario;
	}

	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}

	public Integer getSalaId() {
		return salaId;
	}

	public void setSalaId(Integer salaId) {
		this.salaId = salaId;
	}

	public Integer getFilmeId() {
		return filmeId;
	}

	public void setFilmeId(Integer filmeId) {
		this.filmeId = filmeId;
	}
	
	public Sessao toSessao(FilmeDao filmeDao, SalaDao salaDao) {
		Filme filme = filmeDao.findOne(filmeId);
		Sala sala = salaDao.findOne(salaId);
		
		Sessao sessao = new Sessao(horario, filme, sala);
		return sessao;
	}

}