package org.example.springbookapi.registry;

import org.example.springbookapi.api.ServiceClient;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.logging.Logger;

public class ServiceRegistry {
    static ServiceRegistry instance = null;
    private Logger log = Logger.getLogger(String.valueOf(this.getClass()));

    Set<String> registered_services = null;
    HashMap<String, List<String>> params_to_services_map = null;
    int bitMapMaxCount = 0;
    private Map<Integer, String> service_bitmap;
    private Map<String, ServiceBitMap> params_to_service_bitmap;
    private String intersection_strategy = "Bitmap";
    private Map<String, ServiceClient> service_url_registry = null;

    private ServiceRegistry() {
        params_to_services_map = new HashMap<>();
        registered_services = new HashSet<>();
        service_bitmap = new HashMap<>();
        params_to_service_bitmap = new HashMap<>();
        service_url_registry = new HashMap<>();
    }

    public static ServiceRegistry getInstance() {
        if (instance == null) {
            instance = new ServiceRegistry();
        }
        return instance;
    }

    public void register_service(ServiceClient service) throws Exception {
        try {
            if (!registered_services.contains(service.getServiceId())) {
                registered_services.add(service.getServiceId());

                for (String p : service.getSupportedParameters()) {
                    if (!params_to_services_map.containsKey(p)) {
                        params_to_services_map.put(p, new ArrayList<>());
                        params_to_service_bitmap.put(p, new ServiceBitMap());
                    }
                    params_to_services_map.get(p).add(service.getServiceId());
                    params_to_service_bitmap.get(p).set(bitMapMaxCount);
                }
                service_bitmap.put(bitMapMaxCount, service.getServiceId());
                bitMapMaxCount++;
                service_url_registry.put(service.getServiceId(), service);
            } else {
                throw new Exception("service already registered");
            }
        } catch (InvocationTargetException ie) {
            throw new Exception("given service factory is unable to create instance");
        }
    }

    public List<ServiceClient> getServicesForParams(Set<String> parameters) {
        List<String> intersection = null;

        if (intersection_strategy == "Bitmap") {
            intersection = BitMapIntersection.intersect(parameters,
                    params_to_services_map,
                    params_to_service_bitmap,
                    service_bitmap);
        } else {
            intersection = ListIntersection.intersect(parameters,
                    params_to_services_map);
        }

        List<ServiceClient> serviceEndpoints = new ArrayList<>();

        for (String serviceId : intersection) {
            serviceEndpoints.add(service_url_registry.get(serviceId));
        }
        return serviceEndpoints;
    }
}
