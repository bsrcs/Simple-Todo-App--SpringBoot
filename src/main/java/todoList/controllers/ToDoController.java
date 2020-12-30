package todoList.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import todoList.dto.CreateTaskRequestDto;
import todoList.dto.CreateTaskResponseDto;
import todoList.models.Task;
import todoList.models.User;
import todoList.services.ToDoListService;
import todoList.services.UserService;

import java.util.List;

@RestController
@RequestMapping("todo")
public class ToDoController {
    @Autowired
    ToDoListService toDoListService;
    @Autowired
    UserService userService;

    @GetMapping(path = "/tasks")
    public ResponseEntity<List<Task>> getAllTasks(){
       return new ResponseEntity<>(toDoListService.getAllTasks(), HttpStatus.OK);
    }

    @GetMapping(path = "/task/{id}")
    public ResponseEntity<Task> getTask(@PathVariable Long id){
        return new ResponseEntity<>(toDoListService.getTask(id), HttpStatus.OK);
    }

    @PostMapping(path = "/task")
    public ResponseEntity<?> createTask(@RequestBody CreateTaskRequestDto createTaskRequestDto){
        User user = userService.getUser(createTaskRequestDto.getUserId());
        CreateTaskResponseDto response = new CreateTaskResponseDto();

        Task newTaskToSave = new Task();
        newTaskToSave.setUser(user);
        newTaskToSave.setDescription(createTaskRequestDto.getDescription());
        newTaskToSave.setTaskTitle(createTaskRequestDto.getTaskTitle());
        Task savedTask = toDoListService.createOrUpdateTask(newTaskToSave);

        response.setUserId(createTaskRequestDto.getUserId());
        response.setTaskId(savedTask.getId());
        response.setDescription(savedTask.getDescription());
        response.setTaskTitle(savedTask.getTaskTitle());

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

}
