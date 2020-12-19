package todoList.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import todoList.models.Task;
import todoList.repos.ToDoListRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ToDoListServiceImpl implements ToDoListService {

    @Autowired
    ToDoListRepo toDoListRepo;

    @Override
    public List<Task> getAllTasks() {
        // convert Iterable to a List by for-each structure
        try {
            List<Task> tasks=new ArrayList<>();
            for (Task t: toDoListRepo.findAll()) {
                tasks.add(t);
            }
            return tasks;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Task getTask(Long id) {
        try {
            // convert Optional to Task by Optional method isPresent()
            // isPresent() checks if there is a task for the given id in the database
            if(toDoListRepo.findById(id).isPresent()){
                // get() return Task from Optional
                return toDoListRepo.findById(id).get();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Task createOrUpdateTask(Task task) {
        // When you do save on entity with empty id it will do a save
        // When you do save on entity with existing id it will do an update
        return toDoListRepo.save(task);
    }

    @Override
    public void delete(Task task) {
        toDoListRepo.delete(task);
    }



}
