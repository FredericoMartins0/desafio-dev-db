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


    public String obterNome(){
        return nome;
    }

    public void definirNome(String nome){
        this.nome = nome;
    }
}
