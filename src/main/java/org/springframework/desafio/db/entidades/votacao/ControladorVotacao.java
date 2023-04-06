package org.springframework.desafio.db.entidades.votacao;

import jakarta.validation.Valid;
import org.springframework.desafio.db.entidades.dbservante.Dbservante;
import org.springframework.desafio.db.entidades.dbservante.DbservanteRepositorio;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class ControladorVotacao {
    private final VotacaoRepositorio votacoes;

    private final DbservanteRepositorio dbservantes;

    public ControladorVotacao(VotacaoRepositorio votacoes, DbservanteRepositorio dbservantes){
        this.votacoes = votacoes;
        this.dbservantes = dbservantes;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }

    @ModelAttribute("votacao")
    public Votacao carregarDbservanteComVoto(@PathVariable("db_id")int dbServanteId, Map<String, Object> modelo){
        Dbservante dbservante = this.dbservantes.findById(dbServanteId);
        dbservante.definirVotacao(this.votacoes.findByDbId(dbServanteId));
        modelo.put("dbservante",dbservante);
        Votacao votacao = new Votacao();
        dbservante.adicionarVotacao(votacao);
        return votacao;
    }

    @GetMapping("/dbservantes/{db_id}/votacao/novo")
    public String initNovaVotacao(@PathVariable("db_id")int dbservanteId, Map<String, Object> modelo){
        return "dbservantes/criarVotacao";
    }

    @PostMapping("/dbservantes/{db_id}/votacao/novo")
    public String processarNovaVotacao(@Valid Votacao votacao, BindingResult resultado){
        if(resultado.hasErrors()){
            return "dbservantes/criarVotacao";
        }else{
            this.votacoes.save(votacao);
            return "redirect:/dbservantes/{db_id}";
        }
    }
}
