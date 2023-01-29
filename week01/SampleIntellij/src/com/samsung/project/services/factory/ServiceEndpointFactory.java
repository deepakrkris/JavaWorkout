package com.samsung.project.services.factory;

import com.samsung.project.ServiceEndpoint;
import com.samsung.project.model.InventoryItem;

import java.lang.reflect.InvocationTargetException;

public abstract class ServiceEndpointFactory<T extends ServiceEndpoint<InventoryItem>> {
    protected final Class<T> endpointClass;

    public ServiceEndpointFactory(Class<T> endpointClass) {
        this.endpointClass = endpointClass;
    }

    public abstract T createServiceEndpoint() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException;
}
