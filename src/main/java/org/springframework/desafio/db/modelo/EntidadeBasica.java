package org.springframework.desafio.db.modelo;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;

public class EntidadeBasica implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    public Integer obterId(){
        return id;
    }

    public void definirId(Integer id){
        this.id = id;
    }

    public boolean novoId(){
        return this.id == null;
    }
}
