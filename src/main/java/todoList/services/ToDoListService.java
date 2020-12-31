package todoList.services;
import todoList.models.Task;
import todoList.models.User;

import java.util.List;


public interface ToDoListService {
    List<Task> getAllTasks();
    Task getTask(Long id);
    Task createOrUpdateTask(Task task);
    void delete(Task task);
    List<Task> getAllTasksOfAUser(User user);
}
