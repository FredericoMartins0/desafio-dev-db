package org.springframework.desafio.db.entidades.restaurante;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

public interface RestauranteRepositorio extends Repository<Restaurante,Integer> {

    @Query("SELECT DISTINCT restaurante FROM Restaurante restaurante left join fetch restaurante.dbservantes WHERE restaurante.nome LIKE :nome%")
    @Transactional(readOnly = true)
    Collection<Restaurante> buscarPorNome(@Param("nome") String nome);

    @Query("SELECT DISTINCT restaurante FROM Restaurante restaurante left join fetch restaurante.dbservantes WHERE restaurante.id =:id%")
    @Transactional(readOnly = true)
    Restaurante buscarPorId(@Param("id")Integer id);

    void salvar(Restaurante restaurante);
}
