package com.samsung.project.ratelimit;

import com.samsung.project.ServiceEndpoint;
import com.samsung.project.model.InventoryItem;
import com.sun.jdi.InvocationException;

import java.util.List;
import java.util.Map;

public class RateLimitedInvoker {
    public static List<InventoryItem> invoke(ServiceEndpoint<InventoryItem> service, Map<String, Object> parameters) throws InvocationException, InterruptedException {
        return ((RateLimitedServiceEndpointProxy) service).invokeDelegate(parameters);
    }
}
