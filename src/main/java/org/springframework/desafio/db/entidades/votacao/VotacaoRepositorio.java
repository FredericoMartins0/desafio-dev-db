package org.springframework.desafio.db.entidades.votacao;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface VotacaoRepositorio extends Repository<Votacao, Integer> {

	void save(Votacao votacao) throws DataAccessException;

	List<Votacao> findByDbId(Integer dbId);

}
