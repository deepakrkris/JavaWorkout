package org.example.springbookapi.api;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ServiceClient implements ServiceEndpoint<Object> {

    protected final String serviceId;
    protected final String url;
    protected final int maxConcurrentInvocations;
    protected final Set<String> supportedParams;

    public ServiceClient(String url, String serviceId, int maxConcurrentInvocations, Set<String> supportedParams) {
        this.url = url;
        this.serviceId = serviceId;
        this.maxConcurrentInvocations = maxConcurrentInvocations;
        this.supportedParams = supportedParams;
    }

    /**
     * @return the maximum number of concurrent invocations allowed, or {@code -1}
     * if unlimited.
     */
    public int getMaxConcurrentInvocations() {
        return maxConcurrentInvocations;
    }

    /**
     * @return the set of parameter names supported by this service
     */
    public Set<String> getSupportedParameters() {
        return supportedParams;
    }

    public String getServiceId() {
        return serviceId;
    }

    public String getUrl() {
        return url;
    }

    public List<Object> invoke(Map<String, Object> queryParams) throws InterruptedException {
        StringBuilder endpointUrlBuilder = new StringBuilder(url + "?");

        queryParams.forEach((key, value) -> {
            endpointUrlBuilder.append(key).append("=").append(value).append("&");
        });

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<Object>> data = restTemplate.exchange(endpointUrlBuilder.toString(), HttpMethod.GET, null, new ParameterizedTypeReference<List<Object>>() {
        });

        return data.getBody();
    }
}
