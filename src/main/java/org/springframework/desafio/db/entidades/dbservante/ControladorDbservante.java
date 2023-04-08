package org.springframework.desafio.db.entidades.dbservante;

import jakarta.validation.Valid;
import org.springframework.desafio.db.entidades.restaurante.Restaurante;
import org.springframework.desafio.db.entidades.restaurante.RestauranteRepositorio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
public class ControladorDbservante {

	private static final String VISUALIZACAO_CRIACAO = "dbservantes/CreateDbservante";

	private final DbservanteRepositorio dbservantes;

	private final RestauranteRepositorio restaurantes;

	public ControladorDbservante(DbservanteRepositorio dbr, RestauranteRepositorio restaurantes) {
		this.dbservantes = dbr;
		this.restaurantes = restaurantes;
	}

	@ModelAttribute("restaurante")
	public Restaurante buscarRestaurante(@PathVariable("restauranteId") int restauranteId) {
		return this.restaurantes.findById(restauranteId);
	}

	@InitBinder("restaurante")
	public void initRestauranteBinder(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@InitBinder("dbservante")
	public void initDbservanteBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(new ValidadorDbservante());
	}

	@GetMapping("/dbservantes/novo")
	public String initCriacaoDbservante(Restaurante restaurante, ModelMap modelo) {
		Dbservante dbservante = new Dbservante();
		restaurante.adicionarDbservante(dbservante);
		modelo.put("dbservante", dbservante);
		return VISUALIZACAO_CRIACAO;
	}

	@PostMapping("/dbservantes/novo")
	public String processarCriacaoDbservante(Restaurante restaurante, @Valid Dbservante dbservante,
			BindingResult resultado, ModelMap modelo) {
		if (StringUtils.hasLength(dbservante.obterNome()) && dbservante.novoId()
				&& restaurante.obterDbservantePorNome(dbservante.obterNome(), true) != null) {
			resultado.rejectValue("nome", "duplicate", "já existe dbservante");
		}
		restaurante.adicionarDbservante(dbservante);
		if (resultado.hasErrors()) {
			modelo.put("dbservante", dbservante);
			return VISUALIZACAO_CRIACAO;
		}
		else {
			this.dbservantes.save(dbservante);
			return "redirect:/dbservantes/{db_id}";
		}
	}

	@GetMapping("/dbservantes/{db_id}/editar")
	public String initEditarDbservante(@PathVariable("db_id") int dbservanteId, ModelMap modelo) {
		Dbservante dbservante = this.dbservantes.findById(dbservanteId);
		modelo.put("dbservante", dbservante);
		return VISUALIZACAO_CRIACAO;
	}

	@PostMapping("/dbservantes/{db_id}/editar")
	public String processarEdicaoDbservante(@Valid Dbservante dbservante, BindingResult resultado,
			Restaurante restaurante, ModelMap modelo) {
		if (resultado.hasErrors()) {
			dbservante.definirRestaurante(restaurante);
			modelo.put("dbservante", dbservante);
			return VISUALIZACAO_CRIACAO;
		}
		else {
			restaurante.adicionarDbservante(dbservante);
			this.dbservantes.save(dbservante);
			return "redirect:/restaurantes/{restauranteId}";
		}
	}

}
