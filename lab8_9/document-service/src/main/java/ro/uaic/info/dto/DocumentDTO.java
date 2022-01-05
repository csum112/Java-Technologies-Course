package ro.uaic.info.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import ro.uaic.info.entities.Document;
import ro.uaic.info.multipart.FileUploadBody;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Builder(toBuilder = true)
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public final class DocumentDTO {
    private Long documentID;
    private String username;
    private String fileName;
    private byte[] data;
    private Set<Long> references;

    @JsonIgnore
    public static DocumentDTO of(FileUploadBody fileUploadBody) throws IOException {
        final DocumentDTOBuilder builder = DocumentDTO.builder()
                .fileName(fileUploadBody.getFileName())
                .data(fileUploadBody.getFile().readAllBytes());

        if (fileUploadBody.getReferences() != null) {
            final Set<Long> references = Arrays.stream(fileUploadBody.getReferences()
                    .split(","))
                    .map(Long::parseLong)
                    .collect(Collectors.toSet());
            builder.references(references);
        } else {
            builder.references(new HashSet<>());
        }

        return builder.build();
    }

    @JsonIgnore
    public static DocumentDTO of(Document document) {
        final Set<Long> references = document.getReferences()
                .stream()
                .map(Document::getId)
                .collect(Collectors.toSet());
        return DocumentDTO.builder()
                .username(document.getUser().getUsername())
                .documentID(document.getId())
                .fileName(document.getName())
                .data(document.getData())
                .references(references)
                .build();
    }
}
