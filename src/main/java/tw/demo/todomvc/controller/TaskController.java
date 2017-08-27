package tw.demo.todomvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.demo.todomvc.model.Task;
import tw.demo.todomvc.service.TaskService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/tasks")
public class TaskController {
    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity get(@PathVariable int id) {
        return taskService.get(id);
    }

    @RequestMapping
    public ResponseEntity<List<Map>> find() {
        return taskService.find();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody Task task) {
        return taskService.create(task);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable int id) {
        return taskService.delete(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity update(@PathVariable int id, @RequestBody Task task) {
        return taskService.update(id, task);
    }
}
