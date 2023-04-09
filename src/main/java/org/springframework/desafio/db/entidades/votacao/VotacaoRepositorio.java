package org.springframework.desafio.db.entidades.votacao;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface VotacaoRepositorio extends Repository<Votacao, Integer> {

	void save(Votacao votacao) throws DataAccessException;

	List<Votacao> findByDbId(Integer dbId);

	List<Votacao> findByDiaSemana(String diaSemana);

	List<Votacao> findByData(LocalDate data);

	@Transactional(readOnly = true)
	@Cacheable("votacoes")
	Collection<Votacao> findAll() throws DataAccessException;

}
