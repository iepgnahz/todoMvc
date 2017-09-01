package tw.demo.todomvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import tw.demo.todomvc.Repository.TaskRepository;
import tw.demo.todomvc.model.Task;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task get(int id) {
        Task task = taskRepository.findOne(id);
        if (task != null && task.getAvailable() == 0) {
            return  task;
        }
        return new Task();
    }

    public String create(Task task) {
        Task createdTask = taskRepository.save(task);
        if (createdTask != null) {
            String url = "/tasks/" + createdTask.getId();
            return url;
        }

        return "";
    }

    public List<Task> find() {
        List<Task> tasks = taskRepository.findAll();
        List<Task> targetTasks = tasks.stream().filter(task -> task.getAvailable() == 0).collect(Collectors.toList());

        return targetTasks;
    }

    public HttpStatus delete(int id) {
        Task task = taskRepository.findOne(id);
        if (task != null && task.getAvailable() == 0) {
            task.setAvailable(1);
            taskRepository.save(task);
            return HttpStatus.NO_CONTENT;
        }
        return HttpStatus.NOT_FOUND;
    }

    public HttpStatus update(int id, Task task) {
        Task exitedTask = taskRepository.findOne(id);
        if (exitedTask != null && exitedTask.getAvailable() == 0) {
            taskRepository.save(task);
            return HttpStatus.NO_CONTENT;
        }

        return HttpStatus.NOT_FOUND;
    }
}
