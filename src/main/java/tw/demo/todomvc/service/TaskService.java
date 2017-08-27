package tw.demo.todomvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tw.demo.todomvc.Repository.TaskRepository;
import tw.demo.todomvc.model.Task;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public ResponseEntity get(int id) {
        Task task = taskRepository.findOne(id);
        if (task != null && task.getAvailable() == 0) {
            return new ResponseEntity<Map>(task.formatTask(), HttpStatus.OK);
        }
        return new ResponseEntity<Map>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity create(Task task) {
        Task createdTask = taskRepository.save(task);
        if (createdTask != null) {
            String url = "/tasks/" + createdTask.getId();
            return new ResponseEntity<String>(url, HttpStatus.CREATED);
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Map>> find() {
        List<Task> tasks = taskRepository.findAll();
        List<Map> targetTasks = tasks.stream().filter(task -> task.getAvailable() == 0).map(task -> task.formatTask()).collect(Collectors.toList());

        return new ResponseEntity<List<Map>>(targetTasks, HttpStatus.OK);
    }

    public ResponseEntity delete(int id) {
        Task task = taskRepository.findOne(id);
        if (task != null && task.getAvailable() == 0) {
            task.setAvailable(1);
            taskRepository.save(task);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity update(int id, Task task) {
        Task exitedTask = taskRepository.findOne(id);
        if (exitedTask != null && exitedTask.getAvailable() == 0) {
            Task createdTask = new Task(exitedTask.getText(), exitedTask.getCompleted(), 1);
            taskRepository.save(createdTask);
            task.setId(id);
            taskRepository.save(task);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
