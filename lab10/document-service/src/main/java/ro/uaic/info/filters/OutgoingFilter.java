package ro.uaic.info.filters;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.Objects;

@Provider
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class OutgoingFilter implements ContainerResponseFilter {
    private final DocumentCache documentCache;

    @Override
    public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext) throws IOException {
        if (containerRequestContext.getSecurityContext().getUserPrincipal() != null) {
            final boolean isCacheable = Objects.equals(containerRequestContext.getMethod(), "GET") &&
                    containerRequestContext.getUriInfo().getPath().equals("documents");
            if (isCacheable) {
                final String key = containerRequestContext.getSecurityContext().getUserPrincipal().getName();
                documentCache.getCache().put(key, containerResponseContext.getEntity());
                log.info(String.format("Refreshed cache for %s", key));
            }
        }
    }
}
