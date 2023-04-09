package org.springframework.desafio.db.entidades.votacao;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.desafio.db.entidades.restaurante.Restaurante;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface VotacaoRepositorio extends Repository<Votacao, Integer> {

	void save(Votacao votacao) throws DataAccessException;

	@Transactional(readOnly = true)
	@Cacheable("votacoes")
	Collection<Votacao> findAll() throws DataAccessException;

	@Query("SELECT votacao FROM Votacao votacao")
	@Transactional(readOnly = true)
	@Cacheable("votacoes")
	Page<Votacao> findAll(Pageable pageable) throws DataAccessException;

}
