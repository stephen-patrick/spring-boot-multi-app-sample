package sample.task.api.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import sample.task.api.controller.TaskApiController;
import sample.task.model.RequestModels.MultiplyTaskRequest;
import sample.task.model.ResponseModels.BinaryOperationResponse;
import sample.task.service.TaskService;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskApiController.class)
public class TaskApiControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private TaskService taskService;

	@Test
	public void multiply() throws Exception {

		when(this.taskService.multiply(anyObject())).thenReturn(new BinaryOperationResponse(16));

		BinaryOperationResponse response = taskService.multiply(new MultiplyTaskRequest(4, 4));
		System.out.println(response.getResult());

		mockMvc.perform(post("/api/tasks/multiply").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(new MultiplyTaskRequest(4, 4))))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.status", is("success")))
				.andExpect(jsonPath("$.result", is(16)));

	}

}
