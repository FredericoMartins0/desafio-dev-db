package org.springframework.desafio.db.entidades.votacao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.desafio.db.modelo.EntidadeBasica;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.Locale;

@Entity
@Table(name = "votacao")
public class Votacao extends EntidadeBasica {

	private static final long serialVersionUID = 1L;

	@Column(name = "data")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate data;

	@Column(name = "dia_semana")
	private String diaSemana;

	@Column(name = "hora")
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime hora;

	@Column(name = "db_id")
	private Integer dbId;

	public Votacao() {
		this.data = LocalDate.now();
		this.hora = LocalTime.now();
		this.diaSemana = this.data.getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("pt"));
	}

	public LocalDate obterData() {
		return this.data;
	}

	public void definirData(LocalDate data) {
		this.data = data;
	}

	public LocalTime obterHora() {
		return this.hora;
	}

	public void definirHora(LocalTime hora) {
		this.hora = hora;
	}

	public Integer obterDbId() {
		return this.dbId;
	}

	public void definirDbId(Integer dbId) {
		this.dbId = dbId;
	}

	public String obterDiaSemana() {
		return this.diaSemana;
	}

	public void definirDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}

}
