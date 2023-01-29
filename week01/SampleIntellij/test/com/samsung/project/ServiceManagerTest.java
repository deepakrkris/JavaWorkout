package com.samsung.project;

import com.samsung.project.model.InventoryItem;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
}
