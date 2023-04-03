package org.springframework.desafio.db.entidades.dbservante;

import jakarta.validation.Valid;
import org.springframework.desafio.db.entidades.votacao.VotacaoRepositorio;
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

public class ControladorDbservante {

    private static final String VISUALIZACAO_CRIACAO = "dbservantes/CreateDbservanteForm";
    private final DbservanteRepositorio dbservantes;
    private VotacaoRepositorio votacoes;

    public ControladorDbservante(DbservanteRepositorio dbr, VotacaoRepositorio votacaoRep){
        this.dbservantes = dbr;
        this.votacoes = votacaoRep;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }
    @GetMapping("/dbservantes/novo")
    public String initCriacaoDbservante(Map<String, Object> modelo){
        Dbservante dbservante = new Dbservante();
        modelo.put("dbservante",dbservante);
        return VISUALIZACAO_CRIACAO;
    }

    @PostMapping("/dbservantes/novo")
    public String processarCriacaoDbservante(@Valid Dbservante dbservante, BindingResult resultado){
        if(resultado.hasErrors()){
            return VISUALIZACAO_CRIACAO;
        }else{
            this.dbservantes.salvar(dbservante);
            return "redirect:/dbservantes/"+dbservante.obterId();
        }
    }

    @GetMapping("/dbservantes/find")
    public String initFindDbservante(Map<String,Object> modelo){
        modelo.put("dbservante",new Dbservante());
        return "dbservantes/findDbservantes";
    }

    @GetMapping("/dbservantes")
    public String processarFindDebservante(Dbservante dbservante, BindingResult resultado, Map<String,Object> modelo){
        String redirect = "";
        if(dbservante.obterSobrenome() == null){
            dbservante.definirSobrenome("");
        }
        Collection<Dbservante> resultados = this.dbservantes.encontrarPorSobreome(dbservante.obterSobrenome());
        if(resultados.isEmpty()){
            resultado.rejectValue("sobrenome","notFound","not found");
            redirect = "dbservantes/findDbservantes";
        }else if(resultados.size() == 1){
            dbservante = resultados.iterator().next();
            redirect = "redirect:/dbservantes/"+dbservante.obterId();
        }else{
            modelo.put("selecoes",resultados);
            redirect = "dbservantes/listaDbservantes";
        }
        return redirect;
    }

    @GetMapping("/dbservantes/{db_id}/editar")
    public String initEdicaoDbservante(@PathVariable("db_id")int dbId, Model modelo){
        Dbservante dbservante = this.dbservantes.encontrarPorId(dbId);
        modelo.addAttribute(dbservante);
        return VISUALIZACAO_CRIACAO;
    }

    @PostMapping("/dbservantes/{db_id}/editar")
    public String processarEdicaoDbservante(@Valid Dbservante dbservante, BindingResult resultado, @PathVariable("db_id") int dbId){
        if(resultado.hasErrors()){
            return VISUALIZACAO_CRIACAO;
        }else{
            dbservante.definirId(dbId);
            this.dbservantes.salvar(dbservante);
            return "redirect:/dbservantes/{db_id}";
        }

    }

    @GetMapping("/dbservantes/{db_id}")
    public ModelAndView mostrarDbservante(@PathVariable("db_id") int dbId){
        ModelAndView mav = new ModelAndView("dbservantes/detalhesDbservante");
        Dbservante dbservante = this.dbservantes.encontrarPorId(dbId);
        /*
            @TODO pensar a respeito (modelo PetClinic)
            for (Pet pet : owner.getPets()) {
                pet.setVisitsInternal(visits.findByPetId(pet.getId()));
            }
         */
        mav.addObject(dbservante);
        return mav;
    }
}
