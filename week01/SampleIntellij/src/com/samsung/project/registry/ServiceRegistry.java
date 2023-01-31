package com.samsung.project.registry;

import com.samsung.project.InvocationException;
import com.samsung.project.ServiceEndpoint;
import com.samsung.project.model.InventoryItem;
import com.samsung.project.ratelimit.RateLimitedServiceEndpointProxy;
import com.samsung.project.services.factory.ServiceEndpointFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.logging.Logger;

public class ServiceRegistry {
    static ServiceRegistry instance = null;
    private Logger log = Logger.getLogger(String.valueOf(this.getClass()));

    Set<String> registered_services = null;
    private Map<String, ServiceEndpointFactory<? extends ServiceEndpoint<InventoryItem>>> factories = null;
    HashMap<String, List<String>> params_to_services_map = null;
    private final Map<String, Semaphore> semaphores = new HashMap<>();
    int bitMapMaxCount = 0;
    private Map<Integer, String> service_bitmap;
    private Map<String, ServiceBitMap> params_to_service_bitmap;
    private String intersection_strategy = "Bitmap";

    private ServiceRegistry() {
        params_to_services_map = new HashMap<>();
        registered_services = new HashSet<>();
        factories = new HashMap<>();
        service_bitmap = new HashMap<>();
        params_to_service_bitmap = new HashMap<>();
    }

    public static ServiceRegistry getInstance() {
        if (instance == null) {
            instance = new ServiceRegistry();
        }
        return instance;
    }

    public <T extends ServiceEndpoint<InventoryItem>> void register_service(ServiceEndpointFactory<T> factory) throws Exception {
        try {
            T service = factory.createServiceEndpoint();
            Set<String> params = service.getSupportedParameters();
            String service_id = service.getServiceId();
            if (!registered_services.contains(service_id)) {
                registered_services.add(service_id);

                for (String p : params) {
                    if (!params_to_services_map.containsKey(p)) {
                        params_to_services_map.put(p, new ArrayList<>());
                        params_to_service_bitmap.put(p, new ServiceBitMap());
                    }
                    params_to_services_map.get(p).add(service_id);
                    params_to_service_bitmap.get(p).set(bitMapMaxCount);
                }
                service_bitmap.put(bitMapMaxCount, service_id);
                bitMapMaxCount ++;
                factories.put(service_id, factory);
                semaphores.put(service_id, new Semaphore(service.getMaxConcurrentInvocations()));
            } else {
                throw new Exception("service already registered");
            }
        } catch (InvocationTargetException ie) {
            throw new Exception("given service factory is unable to create instance");
        }
    }

    public List<ServiceEndpoint<InventoryItem>> getServicesForParams(Map<String, Object> parameters) throws InvocationException {
        List<String> intersection = null;

        if (intersection_strategy == "Bitmap") {
            intersection = BitMapDedupe.intersect(parameters,
                    params_to_services_map,
                    params_to_service_bitmap,
                    service_bitmap);
        } else {
            intersection = ListIntersection.intersect(parameters,
                    params_to_services_map);
        }

        List<ServiceEndpoint<InventoryItem>> serviceEndpoints = new ArrayList<>();

        for (String serviceId : intersection) {
            try {
                ServiceEndpoint<InventoryItem> serviceEndpoint = factories.get(serviceId).createServiceEndpoint();
                serviceEndpoints.add(new RateLimitedServiceEndpointProxy(serviceEndpoint, semaphores.get(serviceId)));
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            }
        }
        return serviceEndpoints;
    }
}
