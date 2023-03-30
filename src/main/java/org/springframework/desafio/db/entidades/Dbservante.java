package org.springframework.desafio.db.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.desafio.db.modelo.Pessoa;

@Entity
@Table
public class Dbservante extends Pessoa {

    private static final long serialVersionUID = 1L;

    @Column(name = "restaurante")
    private String restaurante;
}
