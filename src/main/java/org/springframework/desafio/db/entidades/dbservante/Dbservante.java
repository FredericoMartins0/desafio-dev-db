package org.springframework.desafio.db.entidades.dbservante;

import jakarta.persistence.*;
import org.springframework.core.style.ToStringCreator;
import org.springframework.desafio.db.entidades.restaurante.Restaurante;
import org.springframework.desafio.db.modelo.Pessoa;

@Entity
@Table(name = "dbservantes")
public class Dbservante extends Pessoa {

    private static final long serialVersionUID = 1L;

    @Column(name="equipe")
    private String equipe;

    @ManyToOne
    @JoinColumn(name = "restaurante_id")
    private Restaurante restaurante;


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

    @Override
    public String toString(){
        return new ToStringCreator(this)
                .append("id",this.obterId()).append("novo",this.novoId()).append("nome",this.obterNome())
                .append("sobrenome",this.obterSobrenome()).append("equipe",this.equipe).toString();
    }

}
