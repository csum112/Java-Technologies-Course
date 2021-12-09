package ro.uaic.info.lab7.mappers;

import ro.uaic.info.lab7.entities.UserFile;

import javax.servlet.http.Part;
import java.io.IOException;

public class FileMapper implements IMapper<Part, UserFile> {
    @Override
    public UserFile apply(Part src) throws IOException {
        final byte[] contents = src.getInputStream().readAllBytes();
        final UserFile file =  new UserFile();
        file.setName(src.getName());
        file.setSize(src.getSize());
        file.setMime(src.getContentType());
        file.setContents(contents);
        return file;
    }
}
