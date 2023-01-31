package com.samsung.project.registry;

import com.samsung.project.InvocationException;
import com.samsung.project.ServiceEndpoint;
import com.samsung.project.model.InventoryItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class ListIntersection {
    private static Logger log = Logger.getLogger(String.valueOf(ListIntersection.class));

    public static List<String> intersect(
            Map<String, Object> parameters,
            HashMap<String, List<String>> params_to_services_map) throws InvocationException {
        List<String> intersection = null;

        log.info("Starting Deduplication : " + System.currentTimeMillis());
        for (String p : parameters.keySet()) {
            List<String> list_of_serviceIds = params_to_services_map.get(p);
            if (intersection == null) {
                intersection = new ArrayList<>(list_of_serviceIds);
            } else {
                intersection.retainAll(list_of_serviceIds);
            }
        }

        if (intersection == null) {
            throw new InvocationException("parameters sent have no supporting services", "SR002");
        }
        log.info("End Deduplication : " + System.currentTimeMillis());
        return intersection;
    }
}
