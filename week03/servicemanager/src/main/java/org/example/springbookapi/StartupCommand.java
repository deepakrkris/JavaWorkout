package org.example.springbookapi;

import org.example.springbookapi.api.TestServiceClient;
import org.example.springbookapi.ratelimit.RateLimitedServiceEndpointProxy;
import org.example.springbookapi.registry.ServiceRegistry;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;
import java.util.logging.Logger;

@Component
public class StartupCommand implements CommandLineRunner {
    private Logger log = Logger.getLogger(String.valueOf(getClass()));


    @Override
    public void run(String... args) throws Exception {

        RateLimitedServiceEndpointProxy service1 = new RateLimitedServiceEndpointProxy("stocks", "http://localhost:8080/invokeStock", 5, new HashSet<String>() {
            {
                add("category");
                add("brand");
                add("model");
                add("zip");
                add("company");
            }
        }, new Semaphore(5));
        ServiceRegistry.getInstance().register_service(service1);

        RateLimitedServiceEndpointProxy service2 = new RateLimitedServiceEndpointProxy("sales", "http://localhost:8080/invokeSales", 5, new HashSet<String>() {
            {
                add("category");
                add("brand");
                add("model");
                add("salesAmount");
                add("date");
            }
        }, new Semaphore(5));
        ServiceRegistry.getInstance().register_service(service2);

        RateLimitedServiceEndpointProxy service3 = new RateLimitedServiceEndpointProxy("reviews", "http://localhost:8080/invokeReviews", 5, new HashSet<String>() {
            {
                add("category");
                add("brand");
                add("model");
                add("rating");
            }
        }, new Semaphore(5));
        ServiceRegistry.getInstance().register_service(service3);

        Set<String> supportedParamsTestClient = new HashSet<>();
        //log.info("generating from " + (i/10));
        for (int j = 1; j <= 130000; j++) {
            supportedParamsTestClient.add("mockParam" + j);
        }

        TestServiceClient service4 = new TestServiceClient("test_client", "http://localhost/test", 5, supportedParamsTestClient);
        ServiceRegistry.getInstance().register_service(service4);
    }
}
