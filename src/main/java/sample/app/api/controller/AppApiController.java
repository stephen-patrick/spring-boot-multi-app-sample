package sample.app.api.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import sample.app.MicroserviceAppConfiguration.TaskServerConfig;
import sample.common.api.models.ApiModels;
import sample.common.api.models.ApiModels.ApiModelResponse;
import sample.common.api.models.ApiModels.ApiResponse;
import sample.common.util.UriUtils;
import sample.task.model.RequestModels.MultiplyTaskRequest;

@RestController
@RequestMapping("/api/")
public class AppApiController {

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private TaskServerConfig taskServerConfig;

	@RequestMapping(value = "tasks/multiply", method = RequestMethod.POST)
	public ApiResponse multiply(@RequestBody MultiplyTaskRequest task, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			return restTemplate.postForObject(UriUtils.createForwardUri(taskServerConfig, request), task,
					ApiModelResponse.class);
		} catch (Throwable t) {
			if (logger.isErrorEnabled()) {
				logger.error("Unable to complete task", t);
			}

			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return new ApiModels.ApiErrorResponse("Error unable to communicate with task server");
		}

	}

}
