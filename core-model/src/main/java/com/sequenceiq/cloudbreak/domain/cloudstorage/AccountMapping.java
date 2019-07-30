package com.sequenceiq.cloudbreak.domain.cloudstorage;

import java.util.HashMap;
import java.util.Map;

public class AccountMapping {

    private Map<String, String> groupMapping = new HashMap<>();

    private Map<String, String> userMapping = new HashMap<>();

    public Map<String, String> getGroupMapping() {
        return groupMapping;
    }

    public void setGroupMapping(Map<String, String> groupMapping) {
        this.groupMapping = groupMapping == null ? new HashMap<>() : groupMapping;
    }

    public Map<String, String> getUserMapping() {
        return userMapping;
    }

    public void setUserMapping(Map<String, String> userMapping) {
        this.userMapping = userMapping == null ? new HashMap<>() : userMapping;
    }

}
