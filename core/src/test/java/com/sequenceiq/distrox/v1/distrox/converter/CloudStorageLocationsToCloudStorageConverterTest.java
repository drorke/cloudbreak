package com.sequenceiq.distrox.v1.distrox.converter;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sequenceiq.common.api.cloudstorage.CloudStorageRequest;
import com.sequenceiq.common.api.cloudstorage.StorageLocationBase;
import com.sequenceiq.common.api.telemetry.response.LoggingResponse;
import com.sequenceiq.common.api.telemetry.response.TelemetryResponse;
import com.sequenceiq.common.model.CloudIdentityType;
import com.sequenceiq.common.model.CloudStorageCdpService;
import com.sequenceiq.environment.api.v1.environment.model.response.DetailedEnvironmentResponse;

class CloudStorageLocationsToCloudStorageConverterTest {

    private CloudStorageLocationsToCloudStorageConverter underTest;

    @BeforeEach
    void setUp() {
        underTest = new CloudStorageLocationsToCloudStorageConverter();
    }

    @Test
    void testConvertWhenEnvironmentDoesNotHaveTelemetry() {
        CloudStorageRequest result = underTest.convert(null, new DetailedEnvironmentResponse());

        Assert.assertNull(result);
    }

    @Test
    void testConvertWhenEnvironmentsTelemetryDoesNotHaveLogging() {
        DetailedEnvironmentResponse environment = new DetailedEnvironmentResponse();
        environment.setTelemetry(new TelemetryResponse());

        CloudStorageRequest result = underTest.convert(null, environment);

        Assert.assertNull(result);
    }

    @Test
    void testConvertWhenCloudStorageLocationsIsNullAndEnvironmentHasTelemetryWithLogging() {
        DetailedEnvironmentResponse environment = new DetailedEnvironmentResponse();
        TelemetryResponse telemetry = new TelemetryResponse();
        telemetry.setLogging(new LoggingResponse());
        environment.setTelemetry(telemetry);

        CloudStorageRequest result = underTest.convert(null, environment);

        assertNotNull(result);
        assertTrue(result.getIdentities().stream().anyMatch(id -> CloudIdentityType.LOG.equals(id.getType())));
    }

    @Test
    void testConvertWhenCloudStorageLocationsIsEmptyAndEnvironmentHasTelemetryWithLogging() {
        DetailedEnvironmentResponse environment = new DetailedEnvironmentResponse();
        TelemetryResponse telemetry = new TelemetryResponse();
        telemetry.setLogging(new LoggingResponse());
        environment.setTelemetry(telemetry);

        CloudStorageRequest result = underTest.convert(new HashSet<>(), environment);

        assertNotNull(result);
        assertTrue(result.getIdentities().stream().anyMatch(id -> CloudIdentityType.LOG.equals(id.getType())));
        assertTrue(result.getLocations().isEmpty());
    }

    @Test
    void testConvertWhenEnvironmentHaveTelemetryAndStorageLocationsIsNotNullOrEmpty() {
        TelemetryResponse telemetry = new TelemetryResponse();
        telemetry.setLogging(new LoggingResponse());
        DetailedEnvironmentResponse environment = new DetailedEnvironmentResponse();
        environment.setTelemetry(telemetry);

        CloudStorageCdpService eStorageLocationType = CloudStorageCdpService.RANGER_ADMIN;
        String eStorageLocationValue = "MYBUCKET/CONTAINER";
        StorageLocationBase storageLocationBase = new StorageLocationBase();
        storageLocationBase.setType(eStorageLocationType);
        storageLocationBase.setValue(eStorageLocationValue);
        Set<StorageLocationBase> storageLocations = Set.of(storageLocationBase);

        CloudStorageRequest result = underTest.convert(storageLocations, environment);

        assertNotNull(result);
        assertTrue(result.getIdentities().stream().anyMatch(id -> CloudIdentityType.LOG.equals(id.getType())));
        assertTrue(result.getLocations().stream().anyMatch(loc -> eStorageLocationType.equals(loc.getType()) && eStorageLocationValue.equals(loc.getValue())));
    }
}