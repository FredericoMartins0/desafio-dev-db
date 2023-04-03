package org.springframework.desafio.db.entidades.votacao;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface VotacaoRepositorio extends Repository<Votacao,Integer> {

    void salvar(Votacao votacao) throws DataAccessException;

    List<Votacao> encontrarDbservantePorId(Integer dbId);
}
