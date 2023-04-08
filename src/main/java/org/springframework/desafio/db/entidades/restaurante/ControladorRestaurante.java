package org.springframework.desafio.db.entidades.restaurante;

import jakarta.validation.Valid;
import org.springframework.desafio.db.entidades.dbservante.Dbservante;
import org.springframework.desafio.db.entidades.votacao.VotacaoRepositorio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.Map;

@Controller
public class ControladorRestaurante {

	private static final String VISUALIZAR_CRUD_RESTAURANTE = "restaurantes/criarOuAtualizarRestaurante";

	private final RestauranteRepositorio restaurantes;

	private final VotacaoRepositorio votacoes;

	public ControladorRestaurante(RestauranteRepositorio restaurantes, VotacaoRepositorio votacoes) {
		this.restaurantes = restaurantes;
		this.votacoes = votacoes;
	}

	@InitBinder
	public void definirCampos(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping("/restaurantes/novo")
	public String initCriacaoRestaurante(Map<String, Object> modelo) {
		Restaurante restaurante = new Restaurante();
		modelo.put("restaurante", restaurante);
		return VISUALIZAR_CRUD_RESTAURANTE;
	}

	@PostMapping("/restaurantes/novo")
	public String processarCriacaoRestaurante(@Valid Restaurante restaurante, BindingResult resultado) {
		if (resultado.hasErrors()) {
			return VISUALIZAR_CRUD_RESTAURANTE;
		}
		else {
			this.restaurantes.save(restaurante);
			return "redirect:/restaurantes/" + restaurante.obterId();
		}
	}

	@GetMapping("/restaurantes/busca")
	public String initBuscaRestaurante(Map<String, Object> modelo) {
		modelo.put("restaurante", new Restaurante());
		return "restaurantes/buscarRestaurantes";
	}

	@GetMapping("/restaurantes")
	public String processarBuscaRestaurante(Restaurante restaurante, BindingResult resultado,
			Map<String, Object> modelo) {
		if (restaurante.obterNome() == null) {
			restaurante.definirNome("");
		}
		Collection<Restaurante> resultados = this.restaurantes.buscarPorNome(restaurante.obterNome());
		if (resultados.isEmpty()) {
			resultado.rejectValue("nome", "notFound", "Restaurante não encontrado");
			return "restaurantes/buscarRestaurantes";
		}
		else if (resultados.size() == 1) {
			restaurante = resultados.iterator().next();
			return "redirect:/restaurantes/" + restaurante.obterId();
		}
		else {
			modelo.put("selecoes", resultados);
			return "restaurantes/listaRestaurantes";
		}
	}

	@GetMapping("/restaurantes/{restauranteId}/editar")
	public String initEdicaoRestaurante(@PathVariable("restauranteId") int restauranteId, Model modelo) {
		Restaurante restaurante = this.restaurantes.findById(restauranteId);
		modelo.addAttribute(restaurante);
		return VISUALIZAR_CRUD_RESTAURANTE;
	}

	@PostMapping("/restaurantes/restauranteId/editar")
	public String processarEdicaoRestaurante(@Valid Restaurante restaurante, BindingResult resultado,
			@PathVariable("restauranteId") int restauranteId) {
		if (resultado.hasErrors()) {
			return VISUALIZAR_CRUD_RESTAURANTE;
		}
		else {
			restaurante.definirId(restauranteId);
			this.restaurantes.save(restaurante);
			return "redirect:/restaurantes/{restauranteId}";
		}
	}

	@GetMapping("/restaurantes/{restauranteId}")
	public ModelAndView mostrarRestaurante(@PathVariable("restauranteId") int restauranteId) {
		ModelAndView mav = new ModelAndView("restaurantes/restauranteDetalhes");
		Restaurante restaurante = this.restaurantes.findById(restauranteId);
		for (Dbservante dbs : restaurante.obterDbservanteInterno()) {
			dbs.definirVotacao(votacoes.findByDbId(dbs.obterId()));
		}
		mav.addObject(restaurante);
		return mav;
	}

}
