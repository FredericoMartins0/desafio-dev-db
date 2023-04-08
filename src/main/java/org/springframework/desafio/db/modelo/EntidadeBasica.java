package org.springframework.desafio.db.modelo;

import jakarta.persistence.*;

import java.io.Serializable;

@MappedSuperclass
public class EntidadeBasica implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	public Integer obterId() {
		return id;
	}

	public void definirId(Integer id) {
		this.id = id;
	}

	public boolean novoId() {
		return this.id == null;
	}

}
