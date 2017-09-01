package tw.demo.todomvc;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import tw.demo.todomvc.controller.TaskController;
import tw.demo.todomvc.model.Task;
import tw.demo.todomvc.service.TaskService;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static io.restassured.module.mockmvc.matcher.RestAssuredMockMvcMatchers.*;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringRunner.class)
public class UnitTestWithRestAssured {

    @Mock
    TaskService taskService;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

//    @Test
//    public void test() throws Exception {
//        given().
//                standaloneSetup(new TaskController(taskService)).
//                param("name", "Johan").
//                when().
//                get("/greeting").
//                then().
//                statusCode(200).
//                body("id", equalTo(1)).
//                body("content", equalTo("Hello, Johan!"));
//    }

}
