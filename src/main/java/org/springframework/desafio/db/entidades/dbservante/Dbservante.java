package org.springframework.desafio.db.entidades.dbservante;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.desafio.db.modelo.Pessoa;

@Entity
@Table(name = "dbservantes")
public class Dbservante extends Pessoa {

    private static final long serialVersionUID = 1L;

    @Column(name="equipe")
    private String equipe;

    public String obterEquipe(){
        return this.equipe;
    }

    public void definirEquipe(String equipe){
        this.equipe = equipe;
    }

}
