package com.samsung.project.services.factory;

import com.samsung.project.services.SellerInventoryService;

import java.lang.reflect.InvocationTargetException;

public class SellerInventoryServiceFactory extends ServiceEndpointFactory<SellerInventoryService> {
    public SellerInventoryServiceFactory() {
        super(SellerInventoryService.class);
    }

    /**
     * @return
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    @Override
    public SellerInventoryService createServiceEndpoint() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return endpointClass.getDeclaredConstructor().newInstance();
    }
}
