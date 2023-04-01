package org.springframework.desafio.db.entidades;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

public interface DbservanteRepositorio extends Repository<Dbservante, Integer> {

    @Query("SELECT DISTINCT dbservante FROM Dbservante dbservante WHERE dbservante.nome LIKE :nome%")
    @Transactional(readOnly = true)
    Collection<Dbservante> encontrarPorNome(@Param("nome") String nome);

    @Query("SELECT FROM Dbservante dbservante WHERE dbservante.id =:id")
    @Transactional(readOnly = true)
    Dbservante encontrarPorId(@Param("id") Integer id);

    void salvar(Dbservante dbservante);
}
