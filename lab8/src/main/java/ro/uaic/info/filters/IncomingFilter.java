package ro.uaic.info.filters;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ro.uaic.info.dto.DocumentDTO;
import ro.uaic.info.entities.Document;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Slf4j
@Provider
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class IncomingFilter implements ContainerRequestFilter {
    private final DocumentCache documentCache;

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        final boolean isCacheable = Objects.equals(containerRequestContext.getMethod(), "GET");
        final String key = containerRequestContext.getSecurityContext().getUserPrincipal().getName();
        if (isCacheable) {
            List<DocumentDTO> res = documentCache.getCache().getIfPresent(key);
            if (res != null) {
                log.info(String.format("Serving from cache for user %s", key));
                containerRequestContext.abortWith(Response.ok(res).build());
            }
        }
    }
}
