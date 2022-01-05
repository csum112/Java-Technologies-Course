package ro.uaic.info.lab8.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import ro.uaic.info.lab8.accessors.DocumentClient;
import ro.uaic.info.lab8.dto.CyclesResponse;
import ro.uaic.info.lab8.dto.DocumentDTO;
import ro.uaic.info.lab8.services.GraphService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Slf4j
@Path("/graph")
public class GraphController {
    @Inject @RestClient
    DocumentClient documentClient;

    @Inject GraphService graphService;

    @GET
    public List<DocumentDTO> getAll() {
        return documentClient.getAll();
    }

    @GET
    @Path("/cycles")
    @Produces(MediaType.APPLICATION_JSON)
    public Response containsCycles() {
        final boolean containsCycles = graphService.containsCycles();
        if (containsCycles) {
            log.info("Document database contains cycles");
        } else {
            log.info("Document database does not contain cycles");
        }
        return Response
                .ok(new CyclesResponse(containsCycles))
                .build();
    }

    @GET
    @Path("/chrono")
    @Produces(MediaType.APPLICATION_JSON)
    public Response chronologicalOrder() {
        final List<DocumentDTO> sorted = graphService.topologicalSort();
        return Response
                .ok(sorted)
                .build();
    }
}
