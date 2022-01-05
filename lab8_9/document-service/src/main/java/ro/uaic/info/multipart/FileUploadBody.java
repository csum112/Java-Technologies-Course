package ro.uaic.info.multipart;


import lombok.Getter;
import lombok.Setter;
import org.jboss.resteasy.annotations.providers.multipart.PartType;

import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;
import java.io.InputStream;

@Getter @Setter
public class FileUploadBody {
    @FormParam("userID")
    @PartType(MediaType.TEXT_PLAIN)
    private Long userID;

    @FormParam("fileName")
    @PartType(MediaType.TEXT_PLAIN)
    private String fileName;

    @FormParam("data")
    @PartType(MediaType.APPLICATION_OCTET_STREAM)
    private InputStream file;
}
