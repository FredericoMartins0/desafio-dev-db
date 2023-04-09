package org.springframework.desafio.db.entidades.votacao;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.desafio.db.entidades.dbservante.Dbservante;
import org.springframework.desafio.db.entidades.dbservante.DbservanteRepositorio;
import org.springframework.desafio.db.entidades.restaurante.Restaurante;
import org.springframework.desafio.db.entidades.restaurante.RestauranteRepositorio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Controller
public class ControladorVotacao {

	private final VotacaoRepositorio votacoes;

	private final RestauranteRepositorio restaurantes;

	public ControladorVotacao(VotacaoRepositorio votacoes, RestauranteRepositorio restaurantes) {
		this.votacoes = votacoes;
		this.restaurantes = restaurantes;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping("/listaVotacao.html")
	public String listarVotacao(@RequestParam(defaultValue = "1") int pagina, Model modelo){
		Votacoes votos = new Votacoes();
		Page<Votacao> paginacao = buscarPagina(pagina);
		return adicionarPaginacaoModelo(pagina,paginacao,modelo);
	}

	private String adicionarPaginacaoModelo(int pagina, Page<Votacao> paginacao, Model modelo){
		List<Votacao> votacaoLista = paginacao.getContent();
		modelo.addAttribute("currentPage",pagina);
		modelo.addAttribute("totalPages",paginacao.getTotalPages());
		modelo.addAttribute("totalItems", paginacao.getTotalElements());
		modelo.addAttribute("listVotacao",votacaoLista);
		return "votacao/listaVotacao";
	}

	private Page<Votacao> buscarPagina(int pagina){
		int tamanho = 5;
		Pageable pageable = PageRequest.of(pagina -1,tamanho);
		return votacoes.findAll(pageable);
	}

	@GetMapping({"/votacao"})
	public @ResponseBody Votacoes mostrarListaVotacao(){
		Votacoes votos = new Votacoes();
		votos.obterVotacoes().addAll(this.votacoes.findAll());
		return votos;
	}

}
