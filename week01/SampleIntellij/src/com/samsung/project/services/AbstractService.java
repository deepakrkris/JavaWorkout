package com.samsung.project.services;

import java.util.Set;

public abstract class AbstractService {
    protected void validate(Set<String> keySet, Set<String> supportedParameters) throws Exception {
        if (!supportedParameters.containsAll(keySet)) {
            for (String k : keySet) {
                if (!supportedParameters.contains(k))
                    throw new Exception("unsupported key " + k);
            }
        }
    }
}
