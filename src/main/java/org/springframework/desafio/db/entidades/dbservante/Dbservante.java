package org.springframework.desafio.db.entidades.dbservante;

import jakarta.persistence.*;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.core.style.ToStringCreator;
import org.springframework.desafio.db.entidades.restaurante.Restaurante;
import org.springframework.desafio.db.entidades.votacao.Votacao;
import org.springframework.desafio.db.modelo.Pessoa;

import java.util.*;

@Entity
@Table(name = "dbservantes")
public class Dbservante extends Pessoa {

    private static final long serialVersionUID = 1L;

    @Column(name="equipe")
    private String equipe;

    @ManyToOne
    @JoinColumn(name = "restaurante_id")
    private Restaurante restaurante;

    @Transient
    private Set<Votacao> votacoes = new LinkedHashSet<>();


    public String obterEquipe(){
        return this.equipe;
    }

    public void definirEquipe(String equipe){
        this.equipe = equipe;
    }

    public Restaurante obterRestaurante(){
        return this.restaurante;
    }

    public void definirRestaurante(Restaurante restaurante){
        this.restaurante = restaurante;
    }

    protected Set<Votacao> obterVotacoesInterna(){
        if(this.votacoes == null){
            this.votacoes = new HashSet<>();
        }
        return this.votacoes;
    }

    protected void definirVotacaoInterna(Collection<Votacao>votacoes){
        this.votacoes = new LinkedHashSet<>(votacoes);
    }

    public void definirVotacao(Collection<Votacao>votacoes){
        definirVotacaoInterna(votacoes);
    }

    public List<Votacao> obterVotacoes(){
        List<Votacao> votacoesOrdenadas = new ArrayList<>(obterVotacoesInterna());
        PropertyComparator.sort(votacoesOrdenadas,new MutableSortDefinition("data",false,false));
        return Collections.unmodifiableList(votacoesOrdenadas);
    }

    public void adicionarVotacao(Votacao votacao){
        obterVotacoesInterna().add(votacao);
        votacao.definirDbId(this.obterId());
    }

    @Override
    public String toString(){
        return new ToStringCreator(this)
                .append("id",this.obterId()).append("novo",this.novoId()).append("nome",this.obterNome())
                .append("sobrenome",this.obterSobrenome()).append("equipe",this.equipe).toString();
    }

}
