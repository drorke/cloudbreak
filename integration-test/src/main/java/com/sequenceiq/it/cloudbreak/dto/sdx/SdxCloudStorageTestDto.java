package com.sequenceiq.it.cloudbreak.dto.sdx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sequenceiq.common.model.FileSystemType;
import com.sequenceiq.it.cloudbreak.Prototype;
import com.sequenceiq.it.cloudbreak.SdxClient;
import com.sequenceiq.it.cloudbreak.context.TestContext;
import com.sequenceiq.it.cloudbreak.dto.AbstractSdxTestDto;
import com.sequenceiq.sdx.api.model.SdxCloudStorageRequest;
import com.sequenceiq.sdx.api.model.SdxClusterResponse;

@Prototype
public class SdxCloudStorageTestDto extends AbstractSdxTestDto<SdxCloudStorageRequest, SdxClusterResponse, SdxCloudStorageTestDto> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SdxCloudStorageTestDto.class);

    protected SdxCloudStorageTestDto(SdxCloudStorageRequest request, TestContext testContext) {
        super(request, testContext);
    }

    protected SdxCloudStorageTestDto(TestContext testContext) {
        super(new SdxCloudStorageRequest(), testContext);
    }

    public SdxCloudStorageTestDto() {
        super(SdxCloudStorageTestDto.class.getSimpleName().toUpperCase());
    }

    @Override
    public SdxCloudStorageTestDto valid() {
        return getCloudProvider().sdxStorage(this);
    }

    public SdxCloudStorageTestDto withFileSystemType(FileSystemType fileSystemType) {
        getRequest().setFileSystemType(fileSystemType);
        return this;
    }

    public SdxCloudStorageTestDto withBaseLocation(String baseLocation) {
        getRequest().setBaseLocation(baseLocation);
        return this;
    }

    public void cleanUp(TestContext context, SdxClient client) {
        LOGGER.debug("SDX Cloud Storage does not have any clean up operation!");
    }
}
