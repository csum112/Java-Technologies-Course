package ro.uaic.info.lab7.factories;

import ro.uaic.info.lab7.entities.UserFile;
import ro.uaic.info.lab7.mappers.FileMapper;
import ro.uaic.info.lab7.mappers.IMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.servlet.http.Part;

@ApplicationScoped
public class AbstractMapperFactory {
    @Produces
    public IMapper<Part, UserFile> createFileMapper() {
        return new FileMapper();
    }
}
