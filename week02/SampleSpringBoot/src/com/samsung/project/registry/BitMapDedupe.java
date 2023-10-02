package com.samsung.project.registry;

import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class BitMapDedupe {
    private static Logger log = Logger.getLogger(String.valueOf(BitMapDedupe.class));

    public static List<String> intersect(
            Map<String, Object> parameters,
            HashMap<String, List<String>> params_to_services_map,
            Map<String, ServiceBitMap> params_to_service_bitmap,
            Map<Integer, String> service_bitmap) {
        log.info("Starting Deduplication : " + System.currentTimeMillis());
        BitSet temp = new BitSet(320000);
        temp.set(0, 320000, true);
        ServiceBitMap intersectionSet = new ServiceBitMap(temp);

        for (String p : parameters.keySet()) {
            List<String> list_of_serviceIds = params_to_services_map.get(p);
            intersectionSet = intersectionSet.intersection(params_to_service_bitmap.get(p));
        }
        log.info("End Deduplication : " + System.currentTimeMillis());
        return intersectionSet.toList(service_bitmap);
    }
}
