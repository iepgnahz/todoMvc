package tw.demo.todomvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        Task task = taskService.get(id);
        if(task.getId() != 0){
            return new ResponseEntity(task, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @RequestMapping
    public ResponseEntity<List<Task>> find() {
        return new ResponseEntity<List<Task>>(taskService.find(),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody Task task) {
        String uri = taskService.create(task);
        if(uri.length() != 0){
            return new ResponseEntity(uri,HttpStatus.CREATED);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable int id) {
        return new ResponseEntity(taskService.delete(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity update(@PathVariable int id, @RequestBody Task task) {
        return new ResponseEntity(taskService.update(id, task));
    }
}
