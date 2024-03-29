package org.example.springbookapi.registry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListIntersection {
    private static Logger log = Logger.getLogger(String.valueOf(ListIntersection.class));

    public static List<String> intersect(
            Set<String> parameters,
            HashMap<String, List<String>> params_to_services_map) {
        List<String> intersection = null;

        log.log(Level.FINEST, "Starting set intersection : " + System.currentTimeMillis());
        for (String p : parameters) {
            List<String> list_of_serviceIds = params_to_services_map.get(p);
            if (intersection == null) {
                intersection = new ArrayList<>(list_of_serviceIds);
            } else {
                intersection.retainAll(list_of_serviceIds);
            }
        }

        log.log(Level.FINEST, "End set intersection : " + System.currentTimeMillis());
        return intersection;
    }
}
