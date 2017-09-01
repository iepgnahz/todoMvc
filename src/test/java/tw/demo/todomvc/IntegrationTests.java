package tw.demo.todomvc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import tw.demo.todomvc.model.Task;
import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = DemoApplication.class)
@Profile("test")
public class IntegrationTests {

    @Autowired
        private TestRestTemplate restTemplate;

    @Test
    public void get_one_task_when_task_id_exist() throws Exception {
        ResponseEntity<Task> responseEntity = restTemplate.getForEntity("/tasks/1",Task.class);
        Task task = responseEntity.getBody();
        Task expectedTask = new Task();
        expectedTask.setId(1);
        expectedTask.setText("zhangpei");
        expectedTask.setAvailable(0);
        expectedTask.setStatus(0);
        HttpStatus httpStatus = responseEntity.getStatusCode();

        assertThat(httpStatus).isEqualTo(HttpStatus.OK);
        assertThat(task).isEqualToComparingFieldByField(expectedTask);
    }
}
