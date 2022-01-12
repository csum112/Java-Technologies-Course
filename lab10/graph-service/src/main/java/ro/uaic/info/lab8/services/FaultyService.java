package ro.uaic.info.lab8.services;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.context.ManagedExecutor;
import org.eclipse.microprofile.context.ThreadContext;
import org.eclipse.microprofile.faulttolerance.*;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import ro.uaic.info.lab8.accessors.DocumentClient;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.util.concurrent.Future;

@Slf4j
@ApplicationScoped
public class FaultyService {
    @Inject @RestClient
    DocumentClient documentClient;

    @Inject
    ManagedExecutor managedExecutor;

    @Retry
    @Timeout(200)
    @Fallback(fallbackMethod = "fallback")
    public String retryTimeoutFallback() {
        log.info("Calling faulty method");
        return documentClient.mightFail(100);
    }

    @CircuitBreaker(requestVolumeThreshold = 2, delay = 10000L)
    public String circuitBreaker() {
        log.info("Calling circuit breaker method");
        return documentClient.mightFail(100);
    }

    @Bulkhead(value = 1, waitingTaskQueue = 100)
    public String bulkheadSemaphore() {
        log.info("Calling bulkhead method");
        return documentClient.mightFail(0);
    }

    @Asynchronous
    @Bulkhead(value = 1, waitingTaskQueue = 100)
    public Future<String> bulkheadThreadPool() {
        log.info("Calling bulkhead method");
        return managedExecutor.completedFuture(documentClient.mightFail(0));
    }



    public String fallback() {
        log.info("Calling fallback");
        return documentClient.fallback();
    }
}
