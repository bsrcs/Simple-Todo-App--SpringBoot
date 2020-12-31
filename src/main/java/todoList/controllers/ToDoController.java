package todoList.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import todoList.dto.UserRequestDto;
import todoList.dto.CreateTaskRequestDto;
import todoList.dto.CreateTaskResponseDto;
import todoList.models.Task;
import todoList.models.User;
import todoList.services.ToDoListService;
import todoList.services.UserService;
import todoList.util.HashUtil;

import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("todo")
public class ToDoController {
    @Autowired
    ToDoListService toDoListService;
    @Autowired
    UserService userService;

    @GetMapping(path = "/tasks")
    public ResponseEntity<?> getAllTasks(@RequestHeader(value = "secret-token") String secret){
        if (checkIfTokenIsValid(secret)) {
            User user = userService.findByUserName(getUserName(secret)).get();
            List<Task> taskList = toDoListService.getAllTasksOfAUser(user);
            return new ResponseEntity<>(taskList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Bad token!", HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping(path = "/task/{id}")
    public ResponseEntity<?> getTask(@RequestHeader(value = "secret-token") String secret,@PathVariable Long id){
        return new ResponseEntity<>(toDoListService.getTask(id), HttpStatus.OK);
    }

    @PostMapping(path = "/task")
    //When we name a header specifically, the header is required by default
    public ResponseEntity<?> createTask(
            @RequestHeader(value = "secret-token") String secret,
            @RequestBody CreateTaskRequestDto createTaskRequestDto){

        if(checkIfTokenIsValid(secret)){
            User user = userService.findByUserName(getUserName(secret)).get();
            CreateTaskResponseDto response = new CreateTaskResponseDto();

            Task newTaskToSave = new Task();
            newTaskToSave.setUser(user);
            newTaskToSave.setDescription(createTaskRequestDto.getDescription());
            newTaskToSave.setTaskTitle(createTaskRequestDto.getTaskTitle());
            Task savedTask = toDoListService.createOrUpdateTask(newTaskToSave);

            response.setUserId(user.getId());
            response.setTaskId(savedTask.getId());
            response.setDescription(savedTask.getDescription());
            response.setTaskTitle(savedTask.getTaskTitle());

            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Bad token passed!",HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping(path = "/user")
    public ResponseEntity<User> createUser(@RequestBody UserRequestDto userRequestDto){
        User user = new User();
        user.setPassword(userRequestDto.getPassword());
        user.setUsername(userRequestDto.getUsername());
        User savedUser = userService.createOrUpdateUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

    @PostMapping(path = "/login")
    public  ResponseEntity<String> login(HttpServletResponse response, @RequestBody UserRequestDto userRequestDto) throws NoSuchAlgorithmException {
        Optional<User> checkedUser = userService.getUserByUsernameAndPassword(userRequestDto.getUsername(), userRequestDto.getPassword());
        if(checkedUser.isPresent()){
            //successful login
            String token=userRequestDto.getUsername()+":"+HashUtil.getHash(userRequestDto.getPassword());
            response.addHeader("secret-token",token);
            return new ResponseEntity<>("Successfully logged in!", HttpStatus.ACCEPTED);
        }
        else{
            //failed login
            return new ResponseEntity<>("Aoh! Something went wrong!",HttpStatus.FORBIDDEN);
        }
    }

    private boolean checkIfTokenIsValid(String token){
        boolean isTokenValid=false;
        //example: csgirl:8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92
        String[] splittedToken = token.split(":");
        String username = splittedToken[0];
        String hashedPassword = splittedToken[1];

        //check the userOptional credentials which is stored in database
        Optional<User> userOptional = userService.findByUserName(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            String realHash = HashUtil.getHash(user.getPassword());
            if (realHash.equals(hashedPassword)) {
                isTokenValid = true;
            }
        }
        return isTokenValid;
    }

    private String getUserName(String token){
        String[] splittedToken = token.split(":");
        String username = splittedToken[0];

        return  username;
    }

}
