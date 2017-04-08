package sample.task.service.impl;

import org.springframework.stereotype.Service;

import sample.task.model.RequestModels.MultiplyTaskRequest;
import sample.task.model.ResponseModels.BinaryOperationResponse;
import sample.task.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

	@Override
	public BinaryOperationResponse multiply(MultiplyTaskRequest task) {
		return new BinaryOperationResponse(task.getX() * task.getY());
	}
}
