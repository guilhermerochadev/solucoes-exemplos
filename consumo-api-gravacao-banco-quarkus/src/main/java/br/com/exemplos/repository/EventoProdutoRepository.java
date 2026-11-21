package br.com.exemplos.repository;

import br.com.exemplos.entity.EventoProduto;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ApplicationScoped
public class EventoProdutoRepository
        implements PanacheRepository<EventoProduto> {

    public Set<Integer> buscarParametrosExistentes(List<Integer> ids) {

        if (ids == null || ids.isEmpty()) {
            return Set.of();
        }

        List<Integer> resultados = getEntityManager()
                .createQuery("""
                        SELECT DISTINCT e.idParametro
                        FROM EventoProduto e
                        WHERE e.idParametro IN :ids
                        """, Integer.class)
                .setParameter("ids", ids)
                .getResultList();

        return new HashSet<>(resultados);
    }

    @Transactional
    public void salvarTodos(List<EventoProduto> entidades) {

        if (entidades == null || entidades.isEmpty()) {
            return;
        }

        persist(entidades);
    }
}