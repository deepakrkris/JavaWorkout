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


public class ServiceManagerTest {

    private Logger log = Logger.getLogger(String.valueOf(this.getClass()));
    static ServiceManager servicemanager;

    static {
        try {
            servicemanager = new ServiceManager();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ServiceManagerTest() throws Exception {
    }

    @Test
    public void invokeTest1() {
        try {
            log.info("Starting execution of invoke");
            Map<String, Object> parameters = new HashMap<>() {
                {
                    put("Location", "CA");
                    put("Price", "10");
                    put("SellerId", "13344");
                }
            };
            List<InventoryItem> results = servicemanager.invoke(parameters);
            for (InventoryItem item : results) {
                log.info("Actual Value = " + item.getItem_id() + "  : " + item.getItem_type());
            }
        } catch (Exception exception) {
            log.log(Level.WARNING, "Exception in execution of execute1GetAllLogFromFirstMovF-" + exception, exception);
            exception.printStackTrace();
            assertFalse(false);
        }
    }

    @Test
    public void invokeTest2() {
        try {
            log.info("Starting execution of invoke");
            Map<String, Object> parameters = new HashMap<>() {
                {
                    put("Price", "12");
                    put("Category", "cat1");
                }
            };
            List<InventoryItem> results = servicemanager.invoke(parameters);
            for (InventoryItem item : results) {
                log.info("Actual Value = " + item.getItem_id() + "  : " + item.getItem_type());
            }
        } catch (Exception exception) {
            log.log(Level.WARNING, "Exception in execution of execute1GetAllLogFromFirstMovF-" + exception, exception);
            exception.printStackTrace();
            assertFalse(false);
        }
    }

    @Test
    public void invokeTest3() {
        try {
            log.info("Starting execution of invoke");
            Map<String, Object> parameters = new HashMap<>() {
                {
                    put("Location", "IND");
                    put("Category", "cat1");
                }
            };
            List<InventoryItem> results = servicemanager.invoke(parameters);
            for (InventoryItem item : results) {
                log.info("Actual Value = " + item.getItem_id() + "  : " + item.getItem_type());
            }
        } catch (Exception exception) {
            log.log(Level.WARNING, "Exception in execution of execute1GetAllLogFromFirstMovF-" + exception, exception);
            exception.printStackTrace();
            assertFalse(false);
        }
    }

    @Test
    public void invokeTest4() {
        try {
            log.info("Starting execution of invoke");
            Map<String, Object> parameters = new HashMap<>() {
                {
                    put("SellerId", "13233");
                    put("Category", "cat1");
                }
            };
            List<InventoryItem> results = servicemanager.invoke(parameters);
            for (InventoryItem item : results) {
                log.info("Actual Value = " + item.getItem_id() + "  : " + item.getItem_type());
            }
        } catch (Exception exception) {
            log.log(Level.WARNING, "Exception in execution of execute1GetAllLogFromFirstMovF-" + exception, exception);
            exception.printStackTrace();
            assertFalse(false);
        }
    }

    @Test
    public void invokeTest5() {
        try {
            MockServiceEndpoint mockService = Mockito.mock(MockServiceEndpoint.class);
            when(mockService.getServiceId()).thenReturn("MockService" + 0);
            when(mockService.getMaxConcurrentInvocations()).thenReturn(1);
            when(mockService.getSupportedParameters()).thenReturn(new HashSet<>(){
                {
                    add("mockParam1");
                }
            });
            when(mockService.invoke(any(HashMap.class))).thenReturn(new ArrayList<InventoryItem>() {
                {
                    add(new InventoryItem("mock_item_id", "mock_item_type"));
                }
            });
            ServiceRegistry.getInstance().register_service(new MockServiceEndpointFactory(mockService));

            log.info("Starting execution of invoke");
            Map<String, Object> parameters = new HashMap<>() {
                {
                    put("mockParam1", "13233");
                }
            };
            List<InventoryItem> results = servicemanager.invoke(parameters);
            for (InventoryItem item : results) {
                log.info("Actual Value = " + item.getItem_id() + "  : " + item.getItem_type());
            }
        } catch (Exception exception) {
            log.log(Level.WARNING, "Exception in execution of execute1GetAllLogFromFirstMovF-" + exception, exception);
            exception.printStackTrace();
            assertFalse(false);
        }
    }

    @Test
    public void invokeTest6() {
        try {
            for (int i = 1; i <= 100; i++) {
                MockServiceEndpoint mockService = Mockito.mock(MockServiceEndpoint.class);
                when(mockService.getServiceId()).thenReturn("MockService" + i);
                when(mockService.getMaxConcurrentInvocations()).thenReturn(1);
                Set<String> supportedParams = new HashSet<>();
                //log.info("generating from " + (i/10));
                for (int j = i - (i%10) + 1 ; j <= (i - (i%10) + 1) + 10 ; j++) {
                    supportedParams.add("mockParam" + j);
                }
                //log.info(supportedParams.toString());
                when(mockService.getSupportedParameters()).thenReturn(supportedParams);
                when(mockService.invoke(any(HashMap.class))).thenReturn(new ArrayList<InventoryItem>() {
                    {
                        add(new InventoryItem("mock_item_id", "mock_item_type"));
                    }
                });
                ServiceRegistry.getInstance().register_service(new MockServiceEndpointFactory(mockService));
            }

            log.info("Starting execution of invoke");
            Map<String, Object> parameters = new HashMap<>();
            String params[] = { "mockParam49", "mockParam51", "mockParam50", "mockParam42", "mockParam41", "mockParam44", "mockParam43", "mockParam46", "mockParam45", "mockParam48", "mockParam47" };
            for (String p : params) {
                parameters.put(p, "mockItem");
            }
            log.info(parameters.toString());
            List<InventoryItem> results = servicemanager.invoke(parameters);
            for (InventoryItem item : results) {
                log.info("Actual Value = " + item.getItem_id() + "  : " + item.getItem_type());
            }
        } catch (Exception exception) {
            log.log(Level.WARNING, "Exception in execution of execute1GetAllLogFromFirstMovF-" + exception, exception);
            exception.printStackTrace();
            assertFalse(false);
        }
    }
}
