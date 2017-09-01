package tw.demo.todomvc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tw.demo.todomvc.Repository.TaskRepository;
import tw.demo.todomvc.controller.TaskController;
import tw.demo.todomvc.model.Task;
import tw.demo.todomvc.service.TaskService;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
//@WebMvcTest(TaskController.class)
public class DemoApplicationUnitTests {

    @Mock
    private TaskRepository taskRepository;

    private TaskService taskService;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        taskService = new TaskService(taskRepository);
        mockMvc = MockMvcBuilders.standaloneSetup(new TaskController(taskService)).build();
    }

    @Test
    public void test_get_one_task_when_id_exist() throws Exception {
        Task task = new Task();
        task.setId(1);
        task.setText("zhangpei");
        task.setStatus(0);
        when(taskRepository.exists(1)).thenReturn(true);
        when(taskRepository.findOne(1)).thenReturn(task);

        mockMvc.perform(get("/tasks/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.text",is("zhangpei")))
                .andExpect(jsonPath("$.status",is(0)));
    }
}
