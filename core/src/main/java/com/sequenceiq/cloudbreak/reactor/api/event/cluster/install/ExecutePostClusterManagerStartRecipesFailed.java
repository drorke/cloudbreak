package com.sequenceiq.cloudbreak.reactor.api.event.cluster.install;

import com.sequenceiq.cloudbreak.reactor.api.event.StackFailureEvent;

public class ExecutePostClusterManagerStartRecipesFailed extends StackFailureEvent {
    public ExecutePostClusterManagerStartRecipesFailed(Long stackId, Exception ex) {
        super(stackId, ex);
    }
}
