package com.samsung.project.services.factory;

import com.samsung.project.services.LocationService;

import java.lang.reflect.InvocationTargetException;

public class LocationServiceFactory extends ServiceEndpointFactory<LocationService> {
    public LocationServiceFactory() {
        super(LocationService.class);
    }

    /**
     * @return
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    @Override
    public LocationService createServiceEndpoint() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return endpointClass.getDeclaredConstructor().newInstance();
    }
}
