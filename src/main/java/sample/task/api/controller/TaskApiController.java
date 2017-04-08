package sample.task.api.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

import sample.common.api.models.ApiModels;
import sample.common.api.models.ApiModels.ApiErrorValidationResponse;
import sample.common.api.models.ApiModels.ApiResponse;
import sample.common.api.models.ApiModels.ApiSuccessResponseWithModelAndMessage;
import sample.common.util.ValidationUtils;
import sample.task.model.RequestModels.MultiplyTaskRequest;
import sample.task.model.ResponseModels.BinaryOperationResponse;
import sample.task.service.TaskService;

@RestController
@RequestMapping("/api/tasks/multiply")
public class TaskApiController {

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	protected TaskService taskService;

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ApiResponse multiply(@RequestBody @Valid MultiplyTaskRequest task, BindingResult result, HttpServletResponse response) {
		try {

			if (result.hasErrors()) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				return new ApiErrorValidationResponse<>("Validation Failed",
						ValidationUtils.createErrorMapWithLabels(result));
			}

			BinaryOperationResponse opResponse = taskService.multiply(task);

			if (!opResponse.isSuccess()) {
				ValidationUtils.rejectValues(result, opResponse.getErrors());
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

				return new ApiErrorValidationResponse<>(
						opResponse.hasErrorMessage() ? opResponse.getErrorMessage() : "Error performing task",
						ValidationUtils.createErrorMapWithLabels(result));
			}

			return new ApiSuccessResponseWithModelAndMessage("Values multiplied successfully", opResponse.getResult());
		} catch (Throwable t) {
			if (logger.isErrorEnabled()) {
				logger.error("Unable to complete task", t);
			}

			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return new ApiModels.ApiErrorResponse("Error unable to complete task");

		}
	}

	@RestControllerAdvice
	public static class GlobalControllerExceptionHandler {

		@ExceptionHandler(MultipartException.class)
		@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
		public ApiResponse handleFileSize(MultipartException e) {
			return new ApiModels.ApiErrorResponse(e.getRootCause().getMessage());
		}

		@ExceptionHandler(Throwable.class)
		@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
		public ApiResponse handleStorageFileNotFound(Throwable e) {
			return new ApiModels.ApiErrorResponse("Error an unexpected error has occurred");
		}
	}
}
