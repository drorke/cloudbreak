package com.sequenceiq.cloudbreak.cm.polling.task;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.net.SocketException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import com.cloudera.api.swagger.CommandsResourceApi;
import com.cloudera.api.swagger.client.ApiClient;
import com.cloudera.api.swagger.client.ApiException;
import com.sequenceiq.cloudbreak.cm.ClouderaManagerOperationFailedException;
import com.sequenceiq.cloudbreak.cm.client.ClouderaManagerApiPojoFactory;
import com.sequenceiq.cloudbreak.cm.polling.ClouderaManagerPollerObject;
import com.sequenceiq.cloudbreak.domain.stack.Stack;

@RunWith(MockitoJUnitRunner.class)
public class AbstractClouderaManagerCommandCheckerTaskTest {

    private static final int ID = 1;

    private static final int FIVE = 5;

    private static final int SIX = 6;

    @Rule
    public final ExpectedException expectedEx = ExpectedException.none();

    private final ApiClient apiClient = Mockito.mock(ApiClient.class);

    private final ClouderaManagerApiPojoFactory clouderaManagerApiPojoFactory = Mockito.mock(ClouderaManagerApiPojoFactory.class);

    private final CommandsResourceApi commandsResourceApi = Mockito.mock(CommandsResourceApi.class);

    private final AbstractClouderaManagerCommandCheckerTask underTest = new ClouderaManagerDecommissionHostListenerTask(clouderaManagerApiPojoFactory);

    @Before
    public void setup() {
        when(clouderaManagerApiPojoFactory.getCommandsResourceApi(apiClient)).thenReturn(commandsResourceApi);
    }

    @Test
    public void testPollingWithFiveInternalServerErrors() throws ApiException {
        Stack stack = new Stack();
        BigDecimal id = new BigDecimal(ID);
        ClouderaManagerPollerObject pollerObject = new ClouderaManagerPollerObject(stack, apiClient, id);
        when(commandsResourceApi.readCommand(id)).thenAnswer(new Http500Answer(FIVE));

        for (int i = 0; i < FIVE; i++) {
            boolean inProgress = underTest.checkStatus(pollerObject);
            assertFalse(inProgress);
        }
        boolean result = underTest.checkStatus(pollerObject);

        assertTrue(result);
    }

    @Test
    public void testPollingWithSixInternalServerErrors() throws ApiException {
        Stack stack = new Stack();
        BigDecimal id = new BigDecimal(1);
        ClouderaManagerPollerObject pollerObject = new ClouderaManagerPollerObject(stack, apiClient, id);
        when(commandsResourceApi.readCommand(id)).thenAnswer(new Http500Answer(SIX));

        expectedEx.expect(ClouderaManagerOperationFailedException.class);
        expectedEx.expectMessage("Operation is considered failed.");

        for (int i = 0; i < SIX; i++) {
            boolean inProgress = underTest.checkStatus(pollerObject);
            assertFalse(inProgress);
        }
        underTest.checkStatus(pollerObject);
    }

    @Test
    public void testPollingWithFiveSocketExceptions() throws ApiException {
        Stack stack = new Stack();
        BigDecimal id = new BigDecimal(ID);
        ClouderaManagerPollerObject pollerObject = new ClouderaManagerPollerObject(stack, apiClient, id);
        SocketException socketException = new SocketException("Network is unreachable (connect failed)");
        ApiException apiException = new ApiException(socketException);
        when(commandsResourceApi.readCommand(id))
                .thenAnswer(new ExceptionThrowingApiCommandAnswer(apiException, apiException, apiException, apiException, apiException));

        for (int i = 0; i < FIVE; i++) {
            boolean inProgress = underTest.checkStatus(pollerObject);
            assertFalse(inProgress);
        }
        boolean result = underTest.checkStatus(pollerObject);

        assertTrue(result);
    }

    @Test
    public void testPollingWithThreeInternalServerErrorAndThreeSocketExceptions() throws ApiException {
        Stack stack = new Stack();
        BigDecimal id = new BigDecimal(1);
        ClouderaManagerPollerObject pollerObject = new ClouderaManagerPollerObject(stack, apiClient, id);
        SocketException socketException = new SocketException("Network is unreachable (connect failed)");
        ApiException socketApiException = new ApiException(socketException);
        ApiException internalServerError = new ApiException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "error");
        when(commandsResourceApi.readCommand(id)).thenAnswer(new ExceptionThrowingApiCommandAnswer(internalServerError, internalServerError,
                internalServerError, socketApiException, socketApiException, socketApiException));

        expectedEx.expect(ClouderaManagerOperationFailedException.class);
        expectedEx.expectMessage("Operation is considered failed.");

        for (int i = 0; i < SIX; i++) {
            boolean inProgress = underTest.checkStatus(pollerObject);
            assertFalse(inProgress);
        }
        underTest.checkStatus(pollerObject);
    }

}