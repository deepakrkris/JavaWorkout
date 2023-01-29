package com.samsung.project.ratelimit;

import com.samsung.project.InvocationException;
import com.samsung.project.ServiceEndpoint;
import com.samsung.project.model.InventoryItem;
import com.samsung.project.services.AbstractService;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class RateLimitedServiceEndpointProxy extends AbstractService implements ServiceEndpoint<InventoryItem> {
    private final ServiceEndpoint<InventoryItem> delegate;
    private final Semaphore semaphore;

    public RateLimitedServiceEndpointProxy(ServiceEndpoint<InventoryItem> delegate, Semaphore semaphore) {
        this.delegate = delegate;
        this.semaphore = semaphore;
    }

    public String getServiceId() {
        return null;
    }

    public int getMaxConcurrentInvocations() {
        return 0;
    }

    public Set<String> getSupportedParameters() {
        return null;
    }

    public List invoke(Map parameters) throws InvocationException {
        throw new InvocationException("This is a proxy class for all service endpoints, do not call the invoke method directly", "SR001");
    }

    public List<InventoryItem> invokeDelegate(Map<String, Object> parameters) throws InterruptedException {
        if (!semaphore.tryAcquire(10, TimeUnit.SECONDS)) {
            throw new IllegalStateException("Too many concurrent invocations for service: " + delegate.getServiceId());
        }
        try {
            return delegate.invoke(parameters);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            semaphore.release();
        }
    }
}
