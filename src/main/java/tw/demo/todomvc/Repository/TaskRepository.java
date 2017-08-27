package tw.demo.todomvc.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import tw.demo.todomvc.model.Task;

@RepositoryRestResource(collectionResourceRel = "task", path = "tasks")
public interface TaskRepository extends JpaRepository<Task, Integer> {
}

