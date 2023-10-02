package org.example.springbookapi.registry;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BitMapIntersection {
    private static Logger log = Logger.getLogger(String.valueOf(BitMapIntersection.class));

    public static List<String> intersect(
            Set<String> parameters,
            HashMap<String, List<String>> params_to_services_map,
            Map<String, ServiceBitMap> params_to_service_bitmap,
            Map<Integer, String> service_bitmap) {
        log.log(Level.FINEST, "Starting set intersection : " + System.currentTimeMillis());
        BitSet temp = new BitSet(320000);
        temp.set(0, 320000, true);
        ServiceBitMap intersectionSet = new ServiceBitMap(temp);

        for (String p : parameters) {
            List<String> list_of_serviceIds = params_to_services_map.get(p);
            intersectionSet = intersectionSet.intersection(params_to_service_bitmap.get(p));
        }
        log.log(Level.FINEST, "End set intersection : " + System.currentTimeMillis());
        return intersectionSet.toList(service_bitmap);
    }
}
