package br.com.caelum.ingresso.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class Carrinho {

	private List<Ingresso> ingressos = new ArrayList<>();
	
	public List<Ingresso> getIngressos() {
		return ingressos;
	}
	
	public void adiciona(Ingresso ingresso) {
		ingressos.add(ingresso);
	}
	
	public BigDecimal getTotal() {
		BigDecimal total = BigDecimal.ZERO;
		
		for (Ingresso ingresso : ingressos) {
			BigDecimal preco = ingresso.getPreco();
			total = total.add(preco);
		}
		
		return total;
	}
	
	public boolean isSelecionado(Lugar lugarDesejado) {
		return ingressos.stream()
			.map(ingresso -> ingresso.getLugar())
			.anyMatch(lugarSelecionado -> lugarSelecionado.equals(lugarDesejado));
	}
	
	public Compra toCompra(Cartao cartao) {
		return new Compra(ingressos, cartao);
	}
	
}
