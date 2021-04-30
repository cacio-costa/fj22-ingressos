package br.com.caelum.ingresso.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.caelum.ingresso.model.Compra;
import br.com.caelum.ingresso.model.Sessao;

public class BrincandoDeJPA {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ingresso-teste");
		EntityManager manager = emf.createEntityManager();
		
		Compra compra = manager.find(Compra.class, 2);
		System.out.println(compra.getCartao().getVencimento());
		
		
		
		/*manager.close();
		emf.close();*/
	}
	
}
