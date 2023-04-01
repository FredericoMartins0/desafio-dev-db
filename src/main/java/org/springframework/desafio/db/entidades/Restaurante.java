package org.springframework.desafio.db.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.desafio.db.modelo.EntidadeNomeada;

import java.util.List;

@Entity
@Table(name="restaurantes")
public class Restaurante extends EntidadeNomeada {

    @Column(name = "pessoas_votantes")
    private List<Dbservante> votantes;
}
