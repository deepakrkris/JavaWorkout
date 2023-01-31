package com.samsung.project;

import com.samsung.project.model.InventoryItem;
import com.samsung.project.ratelimit.RateLimitedInvoker;
import com.samsung.project.registry.ServiceRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Logger;

public class ServiceManager {
    private Logger log = Logger.getLogger(String.valueOf(this.getClass()));

    ServiceRegistry registry = null;
    int numThreads = 100;
    ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(numThreads);

    public ServiceManager() throws Exception {
        BootStrap.startUp();
        registry = ServiceRegistry.getInstance();
    }

    public List<InventoryItem> invoke(Map<String, Object> parameters) throws InterruptedException, ExecutionException, InvocationException {
        log.info("Service Manager : received request " + System.currentTimeMillis());
        List<ServiceEndpoint<InventoryItem>> services = registry.getServicesForParams(parameters);
        List<InventoryItem> result_list = new ArrayList<>();
        List<Future<List<InventoryItem>>> invokeResults = new ArrayList<>();

        for (ServiceEndpoint<InventoryItem> s : services) {
            invokeResults.add(executor.submit(() -> RateLimitedInvoker.invoke(s, parameters)));
        }

        log.info("Service Manager : called services , waiting for response");

        for (Future<List<InventoryItem>> resultFuture : invokeResults) {
            List<InventoryItem> result = resultFuture.get();
            result_list.addAll(result);
        }

        log.info("Service Manager : received response from all service endpoints " + System.currentTimeMillis());

        return result_list;
    }
}
