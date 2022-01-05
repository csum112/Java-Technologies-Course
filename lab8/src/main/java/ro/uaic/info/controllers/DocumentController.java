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

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

@Path("/documents")
@OpenAPIDefinition(info = @Info(title = "Java Technologies Lab8", version = "1.0"))
public class DocumentController {

    private final static Long TEST_USER = 1L;

    @Inject DocumentService documentService;

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces({MediaType.APPLICATION_JSON})
    @APIResponses(value = {
            @APIResponse(responseCode = "201", description = "Upload a document"),
            @APIResponse(responseCode = "403", description = "Requires authentication")
    })
    public Response addDocument(@MultipartForm FileUploadBody body) throws IOException {
        documentService.upload(TEST_USER, DocumentDTO.of(body));
        return Response.status(Response.Status.CREATED)
                .entity(ResponseMessages.CREATED.toString())
                .build();
    }

    @PUT
    @Path("/{documentID}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces({MediaType.APPLICATION_JSON})
    public Response updateDocument(@PathParam("documentID") Long documentID, @MultipartForm FileUploadBody body) throws IOException, IllegalAccessException {
        documentService.update(TEST_USER, documentID, DocumentDTO.of(body));
        return Response.status(Response.Status.ACCEPTED)
                .entity(ResponseMessages.UPDATED.toString())
                .build();
    }

    @DELETE
    @Path("/{documentID}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response deleteDocument(@PathParam("documentID") Long fileId) throws IllegalAccessException {
        documentService.remove(TEST_USER, fileId);
        return Response.status(Response.Status.ACCEPTED)
                .entity(ResponseMessages.DELETED)
                .build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<DocumentDTO> getAllDocuments() {
        return documentService.getAll(TEST_USER);
    }
}
