package ro.uaic.info.lab7.mappers;

import java.io.IOException;

@FunctionalInterface
public interface IMapper<S,D> {
    D apply(S src) throws IOException;
}
