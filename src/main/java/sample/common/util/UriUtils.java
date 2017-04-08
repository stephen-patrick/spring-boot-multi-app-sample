package sample.common.util;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;

import sample.app.MicroserviceAppConfiguration.ServerConfig;

public class UriUtils {

	public static URI createForwardUri(ServerConfig config, HttpServletRequest request) {
		try {
			return new URI(config.getProtocol(), null, config.getHost(), config.getPort(), request.getRequestURI(),
					request.getQueryString(), null);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

}
