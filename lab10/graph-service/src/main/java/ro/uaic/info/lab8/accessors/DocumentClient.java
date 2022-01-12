package ro.uaic.info.lab8.accessors;

import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import ro.uaic.info.lab8.dto.DocumentDTO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.CompletionStage;

@Path("/admin")
@RegisterRestClient
@ClientHeaderParam(name = "Authorization", value = "{credentials}")
public interface DocumentClient {

    @GET
    List<DocumentDTO> getAll();

    @GET
    CompletionStage<List<DocumentDTO>> getAllAsync();

    @GET
    @Path("/mightFail/{rate}")
    @Produces(MediaType.TEXT_PLAIN)
    String mightFail(@PathParam("rate") Integer rate);

    @GET
    @Path("/fallback")
    @Produces(MediaType.TEXT_PLAIN)
    String fallback();

    default String credentials() {
        byte[] credentials = "admin:admin".getBytes(StandardCharsets.UTF_8);
        return "Basic " + Base64.getEncoder()
                .encodeToString(credentials);
    }

}
