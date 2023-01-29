package com.samsung.project.services.factory;

import com.samsung.project.services.InventorySearchService;

import java.lang.reflect.InvocationTargetException;

public class InventorySearchServiceFactory extends ServiceEndpointFactory<InventorySearchService> {
    public InventorySearchServiceFactory() {
        super(InventorySearchService.class);
    }

    /**
     * @return
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    @Override
    public InventorySearchService createServiceEndpoint() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return endpointClass.getDeclaredConstructor().newInstance();
    }
}
