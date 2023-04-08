package org.springframework.desafio.db.sistema;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorCrash {

	@GetMapping("/oups")
	public String dispararExcessao() {
		throw new RuntimeException("Algo de errado ocorreu! Por favor, tente novamente mais tarde!");
	}

}
