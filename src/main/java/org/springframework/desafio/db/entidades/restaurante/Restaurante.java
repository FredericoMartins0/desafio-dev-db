package org.springframework.desafio.db.entidades.restaurante;

import jakarta.persistence.*;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.core.style.ToStringCreator;
import org.springframework.desafio.db.entidades.dbservante.Dbservante;
import org.springframework.desafio.db.modelo.EntidadeNomeada;

import java.util.*;

@Entity
@Table(name = "restaurantes")
public class Restaurante extends EntidadeNomeada {

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurante")
	private Set<Dbservante> dbservantes;

	@Column(name = "votos")
	private Integer votos;

	public Integer obterVotos() {
		return this.votos;
	}

	public void definirVotos(Integer votos) {
		this.votos = votos;
	}

	public void adicionarVoto() {
		this.votos += 1;
	}

	protected Set<Dbservante> obterDbservanteInterno() {
		if (this.dbservantes == null) {
			this.dbservantes = new HashSet<>();
		}
		return this.dbservantes;
	}

	protected void definirDbservantesInterno(Set<Dbservante> dbservantes) {
		this.dbservantes = dbservantes;
	}

	public List<Dbservante> obterDbservantes() {
		List<Dbservante> dbservantesOrdenados = new ArrayList<>(obterDbservanteInterno());
		PropertyComparator.sort(dbservantesOrdenados, new MutableSortDefinition("nome", true, true));
		return Collections.unmodifiableList(dbservantesOrdenados);
	}

	public void adicionarDbservante(Dbservante dbservante) {
		if (dbservante.novoId()) {
			obterDbservanteInterno().add(dbservante);
		}
		dbservante.definirRestaurante(this);
	}

	public Dbservante obterDbservante(Integer id){
		for(Dbservante dbs : obterDbservantes()){
			if(!dbs.novoId()){
				Integer auxId = dbs.obterId();
				if(auxId.equals(id)){
					return dbs;
				}
			}
		}
		return null;
	}

	public Dbservante obterDbservantePorNome(String nome) {
		return obterDbservantePorNome(nome, false);
	}

	public Dbservante obterDbservantePorNome(String nome, boolean novo) {
		nome = nome.toLowerCase();
		for (Dbservante dbs : obterDbservanteInterno()) {
			if (!novo || !dbs.novoId()) {
				String auxNome = dbs.obterNome();
				auxNome = auxNome.toLowerCase();
				if (auxNome.equals(nome)) {
					return dbs;
				}
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return new ToStringCreator(this).append("id", this.obterId())
			.append("novo", this.novoId())
			.append("nome", this.obterNome())
			.append("votos", this.votos)
			.toString();
	}

}
