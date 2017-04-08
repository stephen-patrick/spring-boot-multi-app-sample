package sample;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import sample.app.MicroserviceApp;
import sample.task.TaskApplication;

@SpringBootApplication
public class Application {

	private final static Log logger = LogFactory.getLog(Application.class);

	public static void main(String[] args) {
		logger.info("Starting application");

		logger.info("Starting Microservice application.");
		start(MicroserviceApp.class).run(args);
		logger.info("Starting Microservice application started.");
		
		
		logger.info("Starting Task application server.");
		start(TaskApplication.class).properties("server.port=${taskServer.port:9000}").run(args);
		logger.info("Task application server started.");
		
		
		logger.info("Application Started Successfully");

	}
	

	private static SpringApplicationBuilder start(Object ... sources) {
		return new SpringApplicationBuilder(sources);
	}

}
