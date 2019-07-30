package com.sequenceiq.common.api.cloudstorage;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sequenceiq.common.api.cloudstorage.doc.CloudStorageModelDescription;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountMappingBase implements Serializable {

    @ApiModelProperty(CloudStorageModelDescription.GROUP_MAPPING)
    private Map<String, String> groupMapping = new HashMap<>();

    @ApiModelProperty(CloudStorageModelDescription.USER_MAPPING)
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
