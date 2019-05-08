package com.sequenceiq.environment.api.credential.model.parameters.azure;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class RoleBasedResponse implements Serializable {

    @ApiModelProperty
    private String roleName;

    @ApiModelProperty(hidden = true)
    private String deploymentAddress;

    @ApiModelProperty
    private String spDisplayName;

    @ApiModelProperty
    private Boolean codeGrantFlow;

    @ApiModelProperty
    private String appObjectId;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDeploymentAddress() {
        return deploymentAddress;
    }

    public void setDeploymentAddress(String deploymentAddress) {
        this.deploymentAddress = deploymentAddress;
    }

    public String getSpDisplayName() {
        return spDisplayName;
    }

    public void setSpDisplayName(String spDisplayName) {
        this.spDisplayName = spDisplayName;
    }

    public Boolean getCodeGrantFlow() {
        return codeGrantFlow;
    }

    public void setCodeGrantFlow(Boolean codeGrantFlow) {
        this.codeGrantFlow = codeGrantFlow;
    }

    public String getAppObjectId() {
        return appObjectId;
    }

    public void setAppObjectId(String appObjectId) {
        this.appObjectId = appObjectId;
    }
}
