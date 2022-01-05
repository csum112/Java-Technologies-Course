package ro.uaic.info.lab8.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

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
}
