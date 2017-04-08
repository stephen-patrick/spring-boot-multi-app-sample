package sample.common.api.models;

public class ApiModels {

	private static final String LABEL_ERROR = "error";
	private static final String LABEL_SUCCESS = "success";

	public static class ApiResponse {

		private String status;

		public ApiResponse() {

		}

		public ApiResponse(String status) {
			this.status = status;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

	}

	public static class ApiResponseWithModel extends ApiResponse {

		private Object result;

		public ApiResponseWithModel() {
			super();
		}

		public ApiResponseWithModel(String status, Object model) {
			super(status);
			this.result = model;
		}

		public Object getResult() {
			return result;
		}

		public void setResult(Object result) {
			this.result = result;
		}

	}

	public static class ApiResponseWithMessage extends ApiResponse {

		private String message;

		public ApiResponseWithMessage() {
			super();
		}

		public ApiResponseWithMessage(String status, String message) {
			super(status);
			this.message = message;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

	}

	public static class ApiErrorResponse extends ApiResponseWithMessage {

		private boolean error = Boolean.TRUE;

		public ApiErrorResponse() {
			super();
		}

		public ApiErrorResponse(String message) {
			super(LABEL_ERROR, message);
		}

		public boolean isError() {
			return error;
		}

		public void setError(boolean error) {
			this.error = error;
		}

	}

	public static class ApiErrorValidationResponse<T> extends ApiResponseWithMessage {

		private T validationErrors;
		private boolean error = Boolean.TRUE;

		public ApiErrorValidationResponse() {
			super();
		}

		public ApiErrorValidationResponse(String message, T validationErrors) {
			super(LABEL_ERROR, message);
			this.validationErrors = validationErrors;
		}

		public T getValidationErrors() {
			return validationErrors;
		}

		public boolean isError() {
			return error;
		}

		public void setValidationErrors(T validationErrors) {
			this.validationErrors = validationErrors;
		}

		public void setError(boolean error) {
			this.error = error;
		}

	}

	public static class ApiSuccessResponseWithModelAndMessage extends ApiResponseWithMessage {

		private Object result;
		private boolean success = true;

		public ApiSuccessResponseWithModelAndMessage() {
			super();
		}

		public ApiSuccessResponseWithModelAndMessage(String message, Object model) {
			super(LABEL_SUCCESS, message);
			this.result = model;
		}

		public Object getResult() {
			return result;
		}

		public boolean isSuccess() {
			return success;
		}

		public void setResult(Object result) {
			this.result = result;
		}

		public void setSuccess(boolean success) {
			this.success = success;
		}

	}

	public static class ApiModelResponse extends ApiResponseWithMessage {

		private Object result;
	
		public ApiModelResponse() {
			super();
		}

		public ApiModelResponse(String message, Object model) {
			super(LABEL_SUCCESS, message);
			this.result = model;
		}

		public Object getResult() {
			return result;
		}

		

		public void setResult(Object result) {
			this.result = result;
		}
	}

}
