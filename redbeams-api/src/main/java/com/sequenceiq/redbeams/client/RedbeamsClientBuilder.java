package com.sequenceiq.redbeams.client;

import static com.sequenceiq.redbeams.api.RedbeamsApi.API_ROOT_CONTEXT;

import com.sequenceiq.cloudbreak.client.AbstractUserCrnServiceClientBuilder;
import com.sequenceiq.cloudbreak.client.ConfigKey;

public class RedbeamsClientBuilder extends AbstractUserCrnServiceClientBuilder<RedbeamsApiUserCrnClient> {
    public RedbeamsClientBuilder(String serviceAddress) {
        super(serviceAddress);
    }

    @Override
    protected RedbeamsApiUserCrnClient createUserCrnClient(String serviceAddress, ConfigKey configKey) {
        return new RedbeamsApiUserCrnClient(serviceAddress, configKey, API_ROOT_CONTEXT);
    }
}
