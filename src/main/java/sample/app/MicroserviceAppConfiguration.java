package sample.app;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

public class MicroserviceAppConfiguration {

	@Configuration
	public static class AppConfiguration {

		@Bean
		public RestTemplate restTemplate() {
			return new RestTemplate();
		}
	}

	public static interface ServerConfig {
		public int getPort();

		public String getHost();

		public String getProtocol();

	}

	@Component
	@ConfigurationProperties(prefix = "taskServer")
	public static class TaskServerConfig implements ServerConfig {
		private String protocol = "http";
		private int port = 9000;
		private String host = "localhost";

		public String getProtocol() {
			return protocol;
		}

		public void setProtocol(String protocol) {
			this.protocol = protocol;
		}

		public String getHost() {
			return host;
		}

		public void setHost(String host) {
			this.host = host;
		}

		public int getPort() {
			return port;
		}

		public void setPort(int port) {
			this.port = port;
		}
	}

}
