package org.springframework.desafio.db.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotEmpty;

@MappedSuperclass
public class Pessoa extends EntidadeBasica{

    private static final long serialVersionUID = 1L;

    @Column(name="nome")
    @NotEmpty
    private String nome;

    @Column(name="sobrenome")
    @NotEmpty
    private String sobrenome;

    public String obterNome(){
        return nome;
    }

    public String obterSobrenome(){
        return sobrenome;
    }

    public String obterNomeCompleto(){
        return nome+" "+sobrenome;
    }

    public void definirNome(String nome){
        this.nome = nome;
    }

    public void definirSobrenome(String sobrenome){
        this.sobrenome = sobrenome;
    }
}
