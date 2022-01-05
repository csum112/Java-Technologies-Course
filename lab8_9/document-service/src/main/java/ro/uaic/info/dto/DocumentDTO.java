package ro.uaic.info.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import ro.uaic.info.entities.Document;
import ro.uaic.info.multipart.FileUploadBody;

import java.io.IOException;

@Builder(toBuilder = true)
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public final class DocumentDTO {
    private Long documentID;
    private String username;
    private String fileName;
    private byte[] data;

    @JsonIgnore
    public static DocumentDTO of(FileUploadBody fileUploadBody) throws IOException {
        return DocumentDTO.builder()
                .fileName(fileUploadBody.getFileName())
                .data(fileUploadBody.getFile().readAllBytes())
                .build();
    }

    @JsonIgnore
    public static DocumentDTO of(Document document) {
        return DocumentDTO.builder()
                .username(document.getUser().getUsername())
                .documentID(document.getId())
                .fileName(document.getName())
                .data(document.getData())
                .build();
    }
}
