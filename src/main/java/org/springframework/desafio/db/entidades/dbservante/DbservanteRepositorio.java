package org.springframework.desafio.db.entidades.dbservante;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

public interface DbservanteRepositorio extends Repository<Dbservante, Integer> {

    @Query("SELECT DISTINCT dbservante FROM Dbservante dbservante WHERE dbservante.sobrenome LIKE :nome%")
    @Transactional(readOnly = true)
    Collection<Dbservante> encontrarPorSobreome(@Param("nome") String nome);

    @Query("SELECT FROM Dbservante dbservante WHERE dbservante.id =:id")
    @Transactional(readOnly = true)
    Dbservante encontrarPorId(@Param("id") Integer id);

    void salvar(Dbservante dbservante);
}
