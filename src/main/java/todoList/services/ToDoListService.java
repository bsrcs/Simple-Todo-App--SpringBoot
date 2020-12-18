package todoList.services;
import todoList.models.Task;
import java.util.List;


public interface ToDoListService {
    List<Task> getAllTasks();
    Task getTask(Long id);
    Task createOrUpdateTask(Task task);
    void delete(Task task);
}
