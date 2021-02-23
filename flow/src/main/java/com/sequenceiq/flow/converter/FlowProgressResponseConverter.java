package com.sequenceiq.flow.converter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import com.sequenceiq.flow.api.model.FlowProgressResponse;
import com.sequenceiq.flow.api.model.FlowStateTransitionResponse;
import com.sequenceiq.flow.core.config.FlowProgressHolder;
import com.sequenceiq.flow.domain.FlowLog;

@Component
public class FlowProgressResponseConverter {

    private static final int UNKNOWN_PROGRESS_PERCENTAGE = -1;

    private static final double MILLISEC_TO_SEC_DOUBLE_DEVIDER = 1000d;

    private final FlowProgressHolder flowProgressHolder;

    public FlowProgressResponseConverter(FlowProgressHolder flowProgressHolder) {
        this.flowProgressHolder = flowProgressHolder;
    }

    public List<FlowProgressResponse> convertList(List<FlowLog> flowLogs) {
        return Optional.ofNullable(flowLogs).orElse(new ArrayList<>())
                .stream().filter(fl -> fl.getFlowId() != null)
                .collect(Collectors.groupingBy(FlowLog::getFlowId))
                .values().stream().map(this::convert)
                .sorted(Comparator.comparingLong(FlowProgressResponse::getCreated).reversed())
                .collect(Collectors.toList());
    }

    public FlowProgressResponse convert(List<FlowLog> flowLogs) {
        FlowProgressResponse response = new FlowProgressResponse();
        if (CollectionUtils.isNotEmpty(flowLogs)) {
            FlowLog lastFlowLog = flowLogs.get(0);
            Optional<String> flowTypeOpt = flowLogs.stream()
                    .filter(fl -> fl.getFlowType() != null).findFirst()
                    .map(fl -> fl.getFlowType().getCanonicalName());
            FlowLog firstFlowLog = flowLogs.get(flowLogs.size() - 1);
            response.setFlowId(lastFlowLog.getFlowId());
            response.setFlowChainId(lastFlowLog.getFlowChainId());
            response.setProgress(flowTypeOpt.map(s ->
                    flowProgressHolder.getProgressPercentageForState(s, lastFlowLog.getCurrentState()))
                    .orElse(UNKNOWN_PROGRESS_PERCENTAGE));
            List<FlowLog> reversedFlowLogs = flowLogs.stream().
                    sorted(Comparator.comparingLong(FlowLog::getCreated))
                    .collect(Collectors.toList());
            response.setTransitions(createFlowStateTransitions(reversedFlowLogs, firstFlowLog.getCreated()));
            response.setResourceId(lastFlowLog.getResourceId());
            response.setFinalized(lastFlowLog.getFinalized());
            response.setCreated(firstFlowLog.getCreated());
            if (lastFlowLog.getFinalized()) {
                response.setElapsedTimeInSeconds(getRoundedTimeInSeconds(firstFlowLog.getCreated(), lastFlowLog.getCreated()));
            } else {
                response.setElapsedTimeInSeconds(getRoundedTimeInSeconds(firstFlowLog.getCreated(), new Date().getTime()));
            }
        }
        return response;
    }

    private List<FlowStateTransitionResponse> createFlowStateTransitions(List<FlowLog> reversedFlowLogs, Long currentCreatedTime) {
        List<FlowStateTransitionResponse> transitions = new ArrayList<>();
        for (int flowLogIndex = 0; flowLogIndex < reversedFlowLogs.size(); flowLogIndex++) {
            FlowLog fl = reversedFlowLogs.get(flowLogIndex);
            Double elapsedTime = null;
            if (!fl.getFinalized() && flowLogIndex == reversedFlowLogs.size() - 1) {
                elapsedTime = getRoundedTimeInSeconds(currentCreatedTime, new Date().getTime());
            } else {
                elapsedTime = getRoundedTimeInSeconds(currentCreatedTime, fl.getCreated());
            }
            currentCreatedTime = fl.getCreated();
            transitions.add(convertToFlowTransitionResponse(fl, elapsedTime));
        }
        return transitions;
    }

    private FlowStateTransitionResponse convertToFlowTransitionResponse(FlowLog fl, Double elapsedTime) {
        FlowStateTransitionResponse transition = new FlowStateTransitionResponse();
        transition.setState(fl.getCurrentState());
        transition.setNextEvent(fl.getNextEvent());
        transition.setStatus(fl.getStateStatus().name());
        transition.setElapsedTimeInSeconds(elapsedTime);
        return transition;
    }

    private Double getRoundedTimeInSeconds(Long from, Long to) {
        return  Double.valueOf(new DecimalFormat("#.###").format((to - from) / MILLISEC_TO_SEC_DOUBLE_DEVIDER));
    }
}