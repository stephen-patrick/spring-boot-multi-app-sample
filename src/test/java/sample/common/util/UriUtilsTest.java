package sample.common.util;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import sample.app.MicroserviceAppConfiguration.TaskServerConfig;
import sample.common.util.UriUtils;

public class UriUtilsTest {

	private TaskServerConfig serverConfig;
	private HttpServletRequest request;

	@Before
	public void setup() {
		serverConfig = new TaskServerConfig();
		serverConfig.setPort(8000);
		serverConfig.setHost("localhost");
		serverConfig.setProtocol("https");

		request = mock(HttpServletRequest.class);
	}

	@Test
	public void createForwardUri() {
		when(request.getRequestURI()).thenReturn("/api/test");

		URI uri = UriUtils.createForwardUri(serverConfig, request);

		verify(request).getRequestURI();
		Assert.assertEquals(uri.toString(), "https://localhost:8000/api/test");
	}

	@Test
	public void createForwardUri_throwsException() {

		try {
			when(request.getRequestURI()).thenReturn("api/test");
			UriUtils.createForwardUri(serverConfig, request);
		} catch (RuntimeException e) {
			return;
		}

		Assert.fail("Expected Exception due to to URI Syntax");

	}

}
