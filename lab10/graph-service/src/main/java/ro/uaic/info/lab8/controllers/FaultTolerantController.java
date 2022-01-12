package ro.uaic.info.lab8.controllers;

import ro.uaic.info.lab8.services.FaultyService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Path("/faultTolerant")
public class FaultTolerantController {
    @Inject FaultyService faultyService;

    @GET
    @Path("retry")
    public String retryTimeoutFallback() {
        return faultyService.retryTimeoutFallback();
    }

    @GET
    @Path("circuitBreaker")
    public String circuitBreaker() {
        return faultyService.circuitBreaker();
    }

    @GET
    @Path("bulkheadSemaphore")
    public String semaphore() {
        return faultyService.bulkheadSemaphore();
    }

    @GET
    @Path("bulkheadTP")
    public String threadPool() throws ExecutionException, InterruptedException {
        return faultyService.bulkheadThreadPool().get();
    }
}
