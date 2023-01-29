package com.samsung.project.services;

import com.samsung.project.ServiceEndpoint;
import com.samsung.project.model.InventoryItem;

import java.util.*;

public class PriceListService extends AbstractService implements ServiceEndpoint<InventoryItem> {
    /**
     * @return
     */
    public String getServiceId() {
        return "PriceListService";
    }

    /**
     * @return the maximum number of concurrent invocations allowed, or {@code -1}
     * if unlimited.
     */
    @Override
    public int getMaxConcurrentInvocations() {
        return 1;
    }

    /**
     * @return the set of parameter names supported by this service
     */
    @Override
    public Set<String> getSupportedParameters() {
        return new HashSet<>() {
            {
                add("Price");
                add("Category");
            }
        };
    }

    /**
     * Invoke the service with a map of named parameters (any subset of the supported
     * list of parameters) and return the results.
     *
     * @param parameters a map from parameter names to values
     * @return the results of
     */
    @Override
    public List<InventoryItem> invoke(Map<String, Object> parameters) throws Exception {
        validate(parameters.keySet(), getSupportedParameters());
        Thread.sleep(2000);
        return new ArrayList<>() {
            {
                add(new InventoryItem("1", "A"));
                add(new InventoryItem("2", "A"));
                add(new InventoryItem("3", "A"));
            }
        };
    }
}
