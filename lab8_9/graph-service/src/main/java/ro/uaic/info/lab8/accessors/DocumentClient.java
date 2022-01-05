package ro.uaic.info.lab8.accessors;

import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import ro.uaic.info.lab8.dto.DocumentDTO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
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

    default String credentials() {
        byte[] credentials = "admin:admin".getBytes(StandardCharsets.UTF_8);
        return  "Basic " + Base64.getEncoder()
                .encodeToString(credentials);
    }
}
