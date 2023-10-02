package com.samsung.project;

import com.samsung.project.model.InventoryItem;
import com.samsung.project.services.factory.ServiceEndpointFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class MockServiceEndpointFactory extends ServiceEndpointFactory<MockServiceEndpoint> {
    MockServiceEndpoint instance = null;

    public MockServiceEndpointFactory(MockServiceEndpoint instance) {
        super(MockServiceEndpoint.class);
        this.instance = instance;
    }

    public MockServiceEndpoint createServiceEndpoint() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return instance;
    }
}
