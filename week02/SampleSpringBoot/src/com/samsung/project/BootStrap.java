package com.samsung.project;

import com.samsung.project.registry.ServiceRegistry;
import com.samsung.project.services.factory.InventorySearchServiceFactory;
import com.samsung.project.services.factory.LocationServiceFactory;
import com.samsung.project.services.factory.PriceListServiceFactory;
import com.samsung.project.services.factory.SellerInventoryServiceFactory;

public class BootStrap {
    private BootStrap() {
    }

    public static void startUp() throws Exception {
        try {
            ServiceRegistry registry = ServiceRegistry.getInstance();
            registry.register_service(new LocationServiceFactory());
            registry.register_service(new PriceListServiceFactory());
            registry.register_service(new SellerInventoryServiceFactory());
            registry.register_service(new InventorySearchServiceFactory());
        } catch (InvocationException ie) {
            System.out.println(ie.getMessage());
        }
    }
}
