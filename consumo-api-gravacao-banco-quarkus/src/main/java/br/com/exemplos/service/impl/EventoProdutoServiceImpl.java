package br.com.exemplos.service.impl;

import br.com.exemplos.entity.EventoProduto;
import br.com.exemplos.integration.api.client.EventoProdutoClient;
import br.com.exemplos.integration.api.dto.EventoProdutoApiDTO;
import br.com.exemplos.mapper.EventoProdutoMapper;
import br.com.exemplos.repository.EventoProdutoRepository;
import br.com.exemplos.service.EventoProdutoService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import java.util.List;

@ApplicationScoped
public class EventoProdutoServiceImpl implements EventoProdutoService {

    private static final Logger LOG =
            Logger.getLogger(EventoProdutoServiceImpl.class);

    private final EventoProdutoClient client;
    private final EventoProdutoRepository repository;
    private final EventoProdutoMapper mapper;

    @Inject
    public EventoProdutoServiceImpl(
            @RestClient EventoProdutoClient client,
            EventoProdutoRepository repository,
            EventoProdutoMapper mapper) {

        this.client = client;
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public void sincronizar() {

        LOG.info("Iniciando sincronização de eventos e produtos.");

        // 1. Consulta API externa
        List<EventoProdutoApiDTO> registros =
                client.buscarEventosProdutos();

        if (registros == null || registros.isEmpty()) {
            LOG.info("Nenhum evento retornado pela API externa.");
            return;
        }

        // 2. Identifica os parâmetros recebidos
        List<Integer> ids = registros.stream()
                .map(EventoProdutoApiDTO::idParametro)
                .distinct()
                .toList();

        // 3. Consulta parâmetros existentes no banco
        var existentes = repository.buscarParametrosExistentes(ids);

        // 4. Identifica registros novos
        List<EventoProdutoApiDTO> novos = registros.stream()
                .filter(dto -> !existentes.contains(dto.idParametro()))
                .toList();

        if (novos.isEmpty()) {
            LOG.info("Nenhum novo evento para persistir.");
            return;
        }

        // 5. Converte DTOs em entidades
        List<EventoProduto> entidades = mapper.toEntityList(novos);

        // 6. Persiste registros
        repository.salvarTodos(entidades);

        LOG.infof(
                "Sincronização concluída. Recebidos: %d | Novos: %d",
                registros.size(),
                entidades.size()
        );
    }
}