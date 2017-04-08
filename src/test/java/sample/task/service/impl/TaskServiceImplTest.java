package sample.task.service.impl;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import sample.task.model.RequestModels;
import sample.task.service.TaskService;
import sample.task.service.impl.TaskServiceImpl;

public class TaskServiceImplTest {

    private TaskService taskService;
    
    @Before
    public void setup() {
        taskService = new TaskServiceImpl();
    }
    
    @Test
    public void multiply() {
       Assert.assertEquals(taskService.multiply(
               new RequestModels.MultiplyTaskRequest(4,4)).getResult(),16);
    }
    
    
    
}
