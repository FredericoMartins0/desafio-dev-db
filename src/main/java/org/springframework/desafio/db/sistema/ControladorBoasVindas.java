package org.springframework.desafio.db.sistema;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorBoasVindas {

    @GetMapping("/")
    public String boasVindas(){
        return "home";
    }
}
