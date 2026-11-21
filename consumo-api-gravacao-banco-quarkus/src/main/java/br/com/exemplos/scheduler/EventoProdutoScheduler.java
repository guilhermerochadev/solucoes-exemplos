
package br.com.exemplos.scheduler;

import br.com.exemplos.service.EventoProdutoService;

import io.quarkus.scheduler.Scheduled;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.jboss.logging.Logger;

@ApplicationScoped
public class EventoProdutoScheduler {

    private static final Logger LOG =
            Logger.getLogger(EventoProdutoScheduler.class);

    private final EventoProdutoService service;

    @Inject
    public EventoProdutoScheduler(EventoProdutoService service) {
        this.service = service;
    }

    @Scheduled(
            every = "${evento-produto.scheduler.interval:1m}",
            identity = "evento-produto-scheduler",
            concurrentExecution = Scheduled.ConcurrentExecution.SKIP
    )
    public void executarSincronizacao() {

        long inicio = System.currentTimeMillis();

        LOG.info("Iniciando sincronização automática de eventos e produtos.");

        try {
            service.sincronizar();

            long duracao = System.currentTimeMillis() - inicio;

            LOG.infof(
                    "Sincronização automática finalizada com sucesso. Tempo: %d ms",
                    duracao
            );

        } catch (Exception e) {
            LOG.error(
                    "Erro durante a sincronização automática de eventos e produtos.",
                    e
            );
        }
    }
}
