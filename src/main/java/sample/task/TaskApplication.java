package sample.task;

import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Separate spring boot application that exposes the task API. This application
 * will be executed on an independent server.
 * 
 * 
 * @author stephen
 *
 */
@SpringBootApplication(scanBasePackages = "sample.task")
public class TaskApplication {

}
