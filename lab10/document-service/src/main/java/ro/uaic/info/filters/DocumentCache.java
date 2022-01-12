package ro.uaic.info.filters;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.Getter;

import javax.enterprise.context.ApplicationScoped;
import java.time.Duration;

@ApplicationScoped
public class DocumentCache {

    private final static Integer MAXIMUM_SIZE = 10000;
    private final static Integer EXPIRES_MINUTES = 30;
    @Getter final private Cache<Object, Object> cache = Caffeine.newBuilder()
            .maximumSize(MAXIMUM_SIZE)
            .expireAfterWrite(Duration.ofMinutes(EXPIRES_MINUTES))
            .build();
}
