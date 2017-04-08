# Overview
A simple spring boot project, demonstrating the use of multiple spring boot applications.



### Design And Configuration
The application is deployed on two separate servers.  The task server is deployed on a separate tomcat server. Currently the configuration allows the task server which provides the multiply task to be configured.  These configuration properties include: host, port, and protocol (scheme).  For simplicity the sample takes advantage of spring boot to create two applications at startup.  The first is the main application, and second is the task application.  The main application (MicroserviceApp) delegates to the the task application (TaskApplication) to handle the API request.  The code is subdivided by package and the scanBasePackages attribute is used on the SpringBootApplication to create the application components by package, i.e. the main application is in package "sample.app" and the task application resides in "sample.task".    


#### Running the sample
The sample can be executed as any spring boot application. This can be done in two main ways.  
1) From eclipse run the main application class sample.Application.
2) Using the supplied jar file in the dist directory.  java -jar dist/spring-boot-multi-app-sample-1.0-RELEASE.jar


##### Live Testing (Browser)
Rest Client Firefox plugin was used for testing from browser as no UI is provided.   To test with Rest Client set the header Content-Type as application/json;charset=UTF-8.  Set the body of the request as {"x": 2, "y": 2}.
To test the application the main API resides at  "http://localhost:8080/api/tasks/multiply".  When called this API delegates to the task multiply service found at (default config) "http://localhost:9000/api/tasks/multiply.



    


