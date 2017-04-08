package sample.task.service;

import sample.task.model.RequestModels.MultiplyTaskRequest;
import sample.task.model.ResponseModels.BinaryOperationResponse;

public interface TaskService {
	
	public BinaryOperationResponse multiply(MultiplyTaskRequest task);
}
