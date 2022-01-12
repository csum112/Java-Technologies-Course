package ro.uaic.info.controllers;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import ro.uaic.info.dto.DocumentDTO;
import ro.uaic.info.services.DocumentService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Slf4j
@Path("/admin")
public class AdminController {

    @Inject
    DocumentService documentService;

    @GET
    @RolesAllowed("user")
    @Produces(MediaType.APPLICATION_JSON)
    @Counted(name = "adminGetAllCounter")
    @Timed(name = "adminGetAllTimer")
    public List<DocumentDTO> getAll() {
        log.info("Dumping all documents");
        return documentService.dump();
    }
}
