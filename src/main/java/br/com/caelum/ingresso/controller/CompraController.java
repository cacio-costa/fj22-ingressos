package br.com.caelum.ingresso.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.ingresso.dao.CompraDao;
import br.com.caelum.ingresso.dao.LugarDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.model.Carrinho;
import br.com.caelum.ingresso.model.Cartao;
import br.com.caelum.ingresso.model.Compra;
import br.com.caelum.ingresso.model.form.CarrinhoForm;


@Controller
public class CompraController {
	
	@Autowired
	private SessaoDao sessaoDao;
	
	@Autowired
	private LugarDao lugarDao;
	
	@Autowired
	private Carrinho carrinho;
	
	@Autowired
	private CompraDao compraDao;

	@PostMapping("/compra/ingressos")
	public ModelAndView adicionaIngressos(CarrinhoForm form) {
		ModelAndView view = new ModelAndView("redirect:/compra"); // Boa prática! Redirect-After-Post...
		form.toIngressos(sessaoDao, lugarDao).forEach(carrinho::adiciona);
				
		return view;
	}
	
	@GetMapping("/compra")
	public ModelAndView resumoDaCompra() {
		ModelAndView view = new ModelAndView("compra/pagamento");
		view.addObject("carrinho", carrinho);
		
		return view;
	}
	
	@Transactional
	@PostMapping("/compra/comprar")
	public ModelAndView efetuaCompra(@Valid Cartao cartao, BindingResult br) {
		if (br.hasErrors()) {
			return new ModelAndView("redirect:/compra");
		}
		
		if (cartao.isVencido()) {
			br.rejectValue("vencimento", "Cartão fora da data de validade");
			return new ModelAndView("redirect:/compra");
		}
		
		Compra compra = carrinho.toCompra(cartao);
		compraDao.save(compra);
				
		return new ModelAndView("redirect:/");
	}
	
}
