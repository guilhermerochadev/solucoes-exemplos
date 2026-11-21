package br.com.exemplos.controller;

import br.com.exemplos.service.EventoProdutoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * O Controller é opcional para a execução automática. Ele pode ser útil para permitir uma sincronização manual.
 */
@Path("/api/eventos-produtos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EventoProdutoController {

    @Inject
    EventoProdutoService service;

    @POST
    @Path("/sincronizar")
    public Response sincronizar() {

        service.sincronizar();

        return Response.ok().build();
    }
}
