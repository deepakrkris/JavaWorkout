package com.samsung.project;

import com.samsung.project.model.InventoryItem;
import com.samsung.project.registry.ServiceRegistry;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.assertFalse;


public class LoadTest {

    private Logger log = Logger.getLogger(String.valueOf(this.getClass()));
    static ServiceManager servicemanager;

    static {
        try {
            servicemanager = new ServiceManager();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public LoadTest() throws Exception {
    }

    @Test
    public void load_test() {
        try {
            for (int i = 1; i <= 1000; i++) {
                MockServiceEndpoint mockService = Mockito.mock(MockServiceEndpoint.class);
                when(mockService.getServiceId()).thenReturn("MockService" + i);
                when(mockService.getMaxConcurrentInvocations()).thenReturn(1);
                Set<String> supportedParams = new HashSet<>();
                //log.info("generating from " + (i/10));
                for (int j = 1; j <= 5000 ; j++) {
                    supportedParams.add("mockParam" + j);
                }
                //log.info(supportedParams.toString());
                when(mockService.getSupportedParameters()).thenReturn(supportedParams);
                List<InventoryItem> result = new ArrayList<>();
                result.add(new InventoryItem("mock_item_id", "MockService" + i));
                when(mockService.invoke(any(HashMap.class))).thenReturn(result);
                ServiceRegistry.getInstance().register_service(new MockServiceEndpointFactory(mockService));
            }

            log.info("Starting execution of invoke");
            Map<String, Object> parameters = new HashMap<>();
            for (int j = 100; j <= 150 ; j++) {
                parameters.put("mockParam" + j, "mockItem");
            }
            List<InventoryItem> results = servicemanager.invoke(parameters);
            log.info("size : " + results.size());
            for (InventoryItem r : results)
                log.info(r.getItem_type());
        } catch (Exception exception) {
            log.log(Level.WARNING, "Exception in execution of execute1GetAllLogFromFirstMovF-" + exception, exception);
            exception.printStackTrace();
            assertFalse(false);
        }
    }
}
