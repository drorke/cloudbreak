package com.sequenceiq.distrox.v1.distrox.converter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import com.sequenceiq.common.api.cloudstorage.CloudStorageRequest;
import com.sequenceiq.common.api.cloudstorage.StorageIdentityBase;
import com.sequenceiq.common.api.cloudstorage.StorageLocationBase;
import com.sequenceiq.common.api.telemetry.response.LoggingResponse;
import com.sequenceiq.common.api.telemetry.response.TelemetryResponse;
import com.sequenceiq.common.model.CloudIdentityType;
import com.sequenceiq.environment.api.v1.environment.model.response.DetailedEnvironmentResponse;

@Component
public class CloudStorageLocationsToCloudStorageConverter {

    public CloudStorageRequest convert(Set<StorageLocationBase> cloudStorageLocations, @NotNull DetailedEnvironmentResponse environment) {
        TelemetryResponse telemetry = environment.getTelemetry();
        if (telemetry != null && telemetry.getLogging() != null) {
            LoggingResponse logging = telemetry.getLogging();
            CloudStorageRequest storageRequest = new CloudStorageRequest();
            StorageIdentityBase identity = new StorageIdentityBase();
            identity.setType(CloudIdentityType.LOG);
            identity.setS3(logging.getS3());
            identity.setWasb(logging.getWasb());
            storageRequest.setIdentities(Collections.singletonList(identity));
            if (CollectionUtils.isNotEmpty(cloudStorageLocations)) {
                storageRequest.setLocations(new ArrayList<>(cloudStorageLocations));
            }
            //TODO set S3Guard as soon as it will present in the environment response
            //        storageRequest.setAws();
            return storageRequest;
        }
        return null;
    }
}
