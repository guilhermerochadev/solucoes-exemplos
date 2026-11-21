
package br.com.exemplos.integration.api.client;

import br.com.exemplos.integration.api.dto.EventoProdutoApiDTO;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@Path("/simulacao-api-sinaf")
@RegisterRestClient(configKey = "evento-produto-api")
@Produces(MediaType.APPLICATION_JSON)
public interface EventoProdutoClient {

    @GET
    List<EventoProdutoApiDTO> buscarEventosProdutos();

}
