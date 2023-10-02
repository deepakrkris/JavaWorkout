package org.example.springbookapi;

import org.example.springbookapi.api.ServiceManager;
import org.junit.Test;

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
    public void invokeTest() {
        try {
            
        } catch (Exception exception) {
            log.log(Level.WARNING, "Exception in execution of execute1GetAllLogFromFirstMovF-" + exception, exception);
            exception.printStackTrace();
            assertFalse(false);
        }
    }
}
