package ro.uaic.info.lab8.controllers;

import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import ro.uaic.info.lab8.accessors.DocumentClient;
import ro.uaic.info.lab8.dto.DocumentDTO;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@Path("/graph")
public class GraphController {
    @Inject @RestClient
    DocumentClient documentClient;

    @GET
    public List<DocumentDTO> getAll() {
        return documentClient.getAll();
    }
}
