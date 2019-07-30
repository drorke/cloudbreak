package com.sequenceiq.cloudbreak.template.views;

import java.util.HashMap;
import java.util.Map;

public class AccountMappingView {

    public static final AccountMappingView EMPTY_MAPPING = new AccountMappingView(null, null);

    private final Map<String, String> groupMapping;

    private final Map<String, String> userMapping;

    public AccountMappingView(Map<String, String> groupMapping, Map<String, String> userMapping) {
        this.groupMapping = groupMapping == null ? new HashMap<>() : groupMapping;
        this.userMapping = userMapping == null ? new HashMap<>() : userMapping;
    }

    public Map<String, String> getGroupMapping() {
        return groupMapping;
    }

    public Map<String, String> getUserMapping() {
        return userMapping;
    }

}
