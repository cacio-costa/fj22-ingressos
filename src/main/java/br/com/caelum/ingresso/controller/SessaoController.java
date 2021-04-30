package br.com.caelum.ingresso.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SalaDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.model.Carrinho;
import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.ImagemDeCapa;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.TipoDeIngresso;
import br.com.caelum.ingresso.model.form.SessaoForm;
import br.com.caelum.ingresso.rest.OmdbClient;
import br.com.caelum.ingresso.validacao.GerenciadorDeSessao;

@Controller
public class SessaoController {
	
	@Autowired
	private SalaDao salaDao;
	
	@Autowired
	private FilmeDao filmeDao;
	
	@Autowired
	private SessaoDao sessaoDao;
	
	@Autowired
	private OmdbClient omdbClient;
	
	@Autowired
	private Carrinho carrinho;
	

	@GetMapping("/admin/sessao")
	public ModelAndView formularioSessao(@RequestParam Integer salaId, SessaoForm form) {
		Sala sala = salaDao.findOne(salaId);
		List<Filme> filmes = filmeDao.findAll();
		
		ModelAndView view = new ModelAndView("sessao/sessao");
		view.addObject("sala", sala);
		view.addObject("filmes", filmes);
		view.addObject("form", form);
		
		return view;
	}
	
	@Transactional
	@PostMapping("/admin/sessao")
	public ModelAndView salvaSessao(@Valid SessaoForm form, BindingResult bindResult) {
		if (bindResult.hasErrors()) {
			return formularioSessao(form.getSalaId(), form);
		}
	
		// Tell, Don't Ask
		Sessao sessao = form.toSessao(filmeDao, salaDao);
		
		List<Sessao> existentes = sessaoDao.buscaSessoesDaSala(sessao.getSala());
		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(existentes);
		
		if (gerenciador.cabe(sessao)) {
			sessaoDao.save(sessao);
			return new ModelAndView("redirect:/admin/sala/" + form.getSalaId() + "/sessoes");
		}
		
		return formularioSessao(form.getSalaId(), form);
	}
	
	@GetMapping("/sessao/{id}/lugares")
	public ModelAndView lugaresDaSessao(@PathVariable Integer id) {
		ModelAndView view = new ModelAndView("sessao/lugares");
		
		Sessao sessao = sessaoDao.findOne(id);
		view.addObject("sessao", sessao);
		
		Optional<ImagemDeCapa> imagemDeCapa = omdbClient.recuperaDetalhesDoFilme(sessao.getFilme(), ImagemDeCapa.class);
		view.addObject("imagemCapa", imagemDeCapa.orElse(new ImagemDeCapa()));
		
		view.addObject("tiposDeIngressos", TipoDeIngresso.values());
		view.addObject("carrinho", carrinho);
		
		return view;
	}
	
}



















