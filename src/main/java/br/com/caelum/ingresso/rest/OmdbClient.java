package br.com.caelum.ingresso.rest;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import br.com.caelum.ingresso.model.Filme;

@Component
public class OmdbClient {

	public <T> Optional<T> recuperaDetalhesDoFilme(Filme filme, Class<T> classe) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			
			String url = "http://omdb-fj22.herokuapp.com/movie?title=" + filme.getNome();
			T resposta = restTemplate.getForObject(url, classe);
			
			return Optional.of(resposta);
		} catch (RestClientException e) {
			e.printStackTrace();
			return Optional.empty();
		}
	}
	
}
