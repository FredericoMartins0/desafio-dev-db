package org.springframework.desafio.db.entidades.restaurante;

import jakarta.persistence.*;
import org.springframework.desafio.db.entidades.dbservante.Dbservante;
import org.springframework.desafio.db.modelo.EntidadeNomeada;

import java.util.List;

@Entity
@Table(name="restaurantes")
public class Restaurante extends EntidadeNomeada {

    @OneToMany
    @JoinColumn(name="db_id")
    private List<Dbservante> dbservantes;

    @Column(name="votos")
    private Integer votos;
}
