package org.springframework.desafio.db.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;

@MappedSuperclass
public class EntidadeNomeada extends EntidadeBasica{

    private static final long serialVersionUID = 1L;

    @Column(name = "nome")
    @NotBlank
    private String nome;

    @Column(name = "votos")
    private Integer votos;

    public String obterNome(){
        return nome;
    }

    public Integer obterVotos() {
        return votos;
    }

    public void definirNome(String nome){
        this.nome = nome;
    }

    public void definirVotos(Integer votos){
        this.votos = votos;
    }

    public void adicionarVoto(){
        this.votos += 1;
    }
}
