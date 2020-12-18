package todoList.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import todoList.models.Task;

import java.util.List;

@Repository
public interface ToDoListRepo extends CrudRepository<Task, Long> {
    // Since i do not have any complex query CrudRepo will take care of simple queries that i have.

    // custom repo method
    Task findFirstByTaskTitle(String taskTitle);
}