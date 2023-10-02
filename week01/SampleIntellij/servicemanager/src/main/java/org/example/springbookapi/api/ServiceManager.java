package org.example.springbookapi.api;

import org.example.springbookapi.registry.ServiceRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ServiceManager {
    @RequestMapping(value = "/invoke", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> invoke(@RequestParam Map<String, Object> queryParams) throws InterruptedException {
        RestTemplate restTemplate = new RestTemplate();

        Map<String, Object> response = new HashMap<>();

        List<ServiceClient> services = ServiceRegistry.getInstance().getServicesForParams(queryParams.keySet());

        for (ServiceClient svc : services) {
            List<Object> data = svc.invoke(queryParams);
            response.put(svc.getServiceId(), data);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
