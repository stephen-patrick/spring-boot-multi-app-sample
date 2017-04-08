package sample.task.model;

import java.util.HashMap;
import java.util.Map;

public class ResponseModels {

	public static class BaseResponse {

		private String errorMessage;

		private Map<String, String> errors = new HashMap<String, String>();

		public BaseResponse() {

		}

		public BaseResponse(String errorMessage) {
			this(errorMessage, new HashMap<String, String>());
		}

		public BaseResponse(String errorMessage, Map<String, String> errors) {
			this.errorMessage = errorMessage;
			this.errors = errors;
		}

		public String getErrorMessage() {
			return errorMessage;
		}

		public boolean hasErrorMessage() {
			return errorMessage != null && !errorMessage.trim().equals("");
		}

		public boolean hasErrors() {
			return errors != null && !errors.isEmpty();
		}

		public Map<String, String> getErrors() {
			return errors;
		}
		
		public boolean isSuccess() {
			return !hasErrors() && !hasErrorMessage();
		}
	}

	public static class BinaryOperationResponse extends BaseResponse {

		private int result;

		public BinaryOperationResponse(String errorMessage) {
			super(errorMessage, new HashMap<String, String>());
		}

		public BinaryOperationResponse(String errorMessage, Map<String, String> errors) {
			super(errorMessage, errors);
		}

		public BinaryOperationResponse(int result) {
			super();
			this.result = result;
		}

		public int getResult() {
			return result;
		}
	}

}
