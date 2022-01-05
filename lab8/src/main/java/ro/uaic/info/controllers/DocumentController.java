package ro.uaic.info.controllers;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import ro.uaic.info.dto.DocumentDTO;
import ro.uaic.info.multipart.FileUploadBody;
import ro.uaic.info.services.DocumentService;
import ro.uaic.info.util.ResponseMessages;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.io.IOException;
import java.util.List;

@Path("/documents")
@OpenAPIDefinition(info = @Info(title = "Java Technologies Lab8", version = "1.0"))
public class DocumentController {
    @Inject DocumentService documentService;

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed("user")
    @APIResponses(value = {
            @APIResponse(responseCode = "201", description = "Upload a document"),
            @APIResponse(responseCode = "403", description = "Requires authentication")
    })
    public Response addDocument(@MultipartForm FileUploadBody body, @Context SecurityContext context) throws IOException {
        documentService.upload(context.getUserPrincipal().getName(), DocumentDTO.of(body));
        return Response.status(Response.Status.CREATED)
                .entity(ResponseMessages.CREATED.toString())
                .build();
    }

    @PUT
    @Path("/{documentID}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed("user")
    public Response updateDocument(
            @PathParam("documentID") Long documentID,
            @MultipartForm FileUploadBody body,
            @Context SecurityContext securityContext
    ) throws IOException, IllegalAccessException {
        documentService.update(securityContext.getUserPrincipal().getName(), documentID, DocumentDTO.of(body));
        return Response.status(Response.Status.ACCEPTED)
                .entity(ResponseMessages.UPDATED.toString())
                .build();
    }

    @DELETE
    @Path("/{documentID}")
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed("user")
    public Response deleteDocument(@PathParam("documentID") Long fileId, @Context SecurityContext securityContext) throws IllegalAccessException {
        documentService.remove(securityContext.getUserPrincipal().getName(), fileId);
        return Response.status(Response.Status.ACCEPTED)
                .entity(ResponseMessages.DELETED)
                .build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed("user")
    public List<DocumentDTO> getAllDocuments(@Context SecurityContext securityContext) {
        return documentService.getAll(securityContext.getUserPrincipal().getName());
    }
}
