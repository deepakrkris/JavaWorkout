package com.samsung.project.services.factory;

import com.samsung.project.services.PriceListService;

import java.lang.reflect.InvocationTargetException;

public class PriceListServiceFactory extends ServiceEndpointFactory<PriceListService> {
    public PriceListServiceFactory() {
        super(PriceListService.class);
    }

    /**
     * @return
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    @Override
    public PriceListService createServiceEndpoint() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return endpointClass.getDeclaredConstructor().newInstance();
    }
}
