package com.samsung.project;

import com.samsung.project.model.InventoryItem;

import java.util.List;
import java.util.Map;
import java.util.Set;

class MockServiceEndpoint implements ServiceEndpoint<InventoryItem> {

    public String getServiceId() {
        return null;
    }

    public int getMaxConcurrentInvocations() {
        return 0;
    }

    public Set<String> getSupportedParameters() {
        return null;
    }

    public List<InventoryItem> invoke(Map<String, Object> parameters) throws Exception {
        return null;
    }
}

