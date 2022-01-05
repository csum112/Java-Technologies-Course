package ro.uaic.info.filters;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@Provider
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class IncomingFilter implements ContainerRequestFilter {
    private final DocumentCache documentCache;

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        if (containerRequestContext.getSecurityContext().getUserPrincipal() != null) {
            final boolean isCacheable = Objects.equals(containerRequestContext.getMethod(), "GET");
            final String key = containerRequestContext.getSecurityContext().getUserPrincipal().getName();
            if (isCacheable) {
                Object res = documentCache.getCache().getIfPresent(key);
                if (res != null) {
                    log.info(String.format("Serving from cache for user %s", key));
                    containerRequestContext.abortWith(Response.ok(res).build());
                }
            }
        }
    }
}
