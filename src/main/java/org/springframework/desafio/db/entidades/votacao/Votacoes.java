package org.springframework.desafio.db.entidades.votacao;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Votacoes {
    private List<Votacao> votacoes;

    @XmlElement
    public List<Votacao> obterVotacoes(){
        if(votacoes == null){
            votacoes = new ArrayList<>();
        }
        return votacoes;
    }
}
