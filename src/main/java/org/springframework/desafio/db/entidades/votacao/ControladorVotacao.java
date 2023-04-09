package org.springframework.desafio.db.entidades.votacao;

import jakarta.validation.Valid;
import org.springframework.desafio.db.entidades.dbservante.Dbservante;
import org.springframework.desafio.db.entidades.dbservante.DbservanteRepositorio;
import org.springframework.desafio.db.entidades.restaurante.Restaurante;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

@Controller
public class ControladorVotacao {

	private final VotacaoRepositorio votacoes;

	private final DbservanteRepositorio dbservantes;

	public ControladorVotacao(VotacaoRepositorio votacoes, DbservanteRepositorio dbservantes) {
		this.votacoes = votacoes;
		this.dbservantes = dbservantes;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@ModelAttribute("votacao")
	public Votacao carregarDbservanteComVoto(@PathVariable("db_id") int dbServanteId, Map<String, Object> modelo) {
		Dbservante dbservante = this.dbservantes.findById(dbServanteId);
		dbservante.definirVotacao(this.votacoes.findByDbId(dbServanteId));
		modelo.put("dbservante", dbservante);
		Votacao votacao = new Votacao();
		dbservante.adicionarVotacao(votacao);
		return votacao;
	}

	@GetMapping("/votacao/novo")
	public String initNovaVotacao(Map<String, Object> modelo) {
		return "votacao/criarVotacao";
	}

	@PostMapping("/votacao/novo")
	public String processarNovaVotacao(@Valid Votacao votacao, BindingResult resultado) {
		if (resultado.hasErrors()) {
			return "votacao/criarVotacao";
		}
		else {
			this.votacoes.save(votacao);
			return "redirect:/votacao/{votacao_id}";
		}
	}

	@GetMapping("/votacao/buscar")
	public String initBuscaVotacao(){
		return "/votacao/burcarVotacao";
	}

	@GetMapping("/votacao")
	public String processarBuscaVotacao(Votacao votacao, BindingResult resultado, Map<String, Object> modelo){
		if (votacao.obterDiaSemana() == null) {
			votacao.definirDiaSemana("");
		}
		Collection<Votacao> resultados = this.votacoes.findByData(votacao.obterData());
		if (resultados.isEmpty()) {
			resultado.rejectValue("data", "notFound", "Votação não encontrada");
			return "votacao/buscarVotacao";
		}
		else if (resultados.size() == 1) {
			votacao = resultados.iterator().next();
			return "redirect:/votacao/" + votacao.obterId();
		}
		else {
			modelo.put("selecoes", resultados);
			return "votacao/listaVotacao";
		}
	}

	@GetMapping("/listaVotacao.html")
	public String listarVotacao(Map<String, Object>modelo){
		Votacoes votos = new Votacoes();
		votos.obterVotacoes().addAll(this.votacoes.findAll());
		modelo.put("votacoes",votos);
		return "votacao/listaVotacao";
	}

}
