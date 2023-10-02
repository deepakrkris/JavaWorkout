package org.example.springbookapi.ratelimit;

import org.example.springbookapi.api.ServiceClient;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class RateLimitedServiceEndpointProxy extends ServiceClient {
    private final Semaphore semaphore;

    public RateLimitedServiceEndpointProxy(String serviceId,
                                           String url,
                                           int maxConcurrentInvocations,
                                           Set<String> supportedParams,
                                           Semaphore semaphore) {
        super(url, serviceId, maxConcurrentInvocations, supportedParams);
        this.semaphore = semaphore;
    }

    public List<Object> invoke(Map<String, Object> parameters) throws InterruptedException {
        if (!semaphore.tryAcquire(10, TimeUnit.SECONDS)) {
            throw new IllegalStateException("Too many concurrent invocations for service: " + serviceId);
        }
        try {
            return super.invoke(parameters);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            semaphore.release();
        }
    }
}
