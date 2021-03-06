package todoList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import todoList.models.Task;
import todoList.models.User;
import todoList.services.ToDoListService;
import todoList.services.UserService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class AppRunner {

  /*  @Autowired
    ToDoListService toDoListService;
    @Autowired
    UserService userService;

    @Value("${todoapp.username}")
    String defaultUsername;
    @Value("${todoapp.password}")
    String defaultPass;*/

    public static void main(String[] args) {
        SpringApplication.run(AppRunner.class, args);
    }

    // use this in case of commandlinerunner app
   /* public void run(String... args) throws Exception {
        insertDefaultData();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to TO DO Command Line App!");

        int response;

        //check if user credentials are correct
        Optional<User> user = userService.getUserByUsernameAndPassword(getStringFromUser(scanner,"Enter username: "),
                getStringFromUser(scanner,"Enter password: "));
        if(!user.isPresent()){
            System.out.println("Bad credentials! :| ");
            return;
        }else{
            System.out.println("Welcome " + user.get().getUsername() + " :)");
        }

        // show task options
        do {
            showOptions();
            response=getIntFromUser(scanner);

            switch (response){
                case 1:
                    Task task = new Task();
                    task.setTaskTitle(getStringFromUser(scanner,"Enter the title:"));
                    task.setDescription(getStringFromUser(scanner,"Enter the description:"));
                    toDoListService.createOrUpdateTask(task);
                    break;
                case 2:
                    for (Task t : toDoListService.getAllTasks()) {
                        System.out.println(t);
                    }
                    break;
                case 3:
                    System.out.println("Enter id:");
                    int id = getIntFromUser(scanner);
                    Task taskToUpdate = toDoListService.getTask(Long.valueOf(id));
                    boolean isMarked = taskToUpdate.isMarkedAsComplete();
                    // flip the flag
                    taskToUpdate.setMarkedAsComplete(!isMarked);
                    if(isMarked){
                        System.out.println("Successfully marked as incomplete");
                    }
                    if(!isMarked){
                        System.out.println("Successfully marked as complete");
                    }
                    toDoListService.createOrUpdateTask(taskToUpdate);
                    break;
                case 4:
                    System.out.println("Enter id that you want to delete:");
                    Task taskToDelete = new Task();
                    int idToDelete = getIntFromUser(scanner);
                    taskToDelete.setId(Long.valueOf(idToDelete));
                    toDoListService.delete(taskToDelete);
                    System.out.println("You successfully deleted " + idToDelete + ". task!");
                    break;
                case 5:
                    System.out.println("Enter id that you want to update: ");
                    int idToUpdate = getIntFromUser(scanner);
                    Task taskToUpdateTitle = toDoListService.getTask(Long.valueOf(idToUpdate));
                    System.out.println("Update\n(1)-title\n"+"(2)-description");
                    int titleOrDescriptionChoiceNo = getIntFromUser(scanner);
                    String inputFromUser="";
                    if(titleOrDescriptionChoiceNo == 1){
                        inputFromUser = getStringFromUser(scanner,"Enter new title: ");
                        if(!inputFromUser.trim().equals("")){
                            taskToUpdateTitle.setTaskTitle(inputFromUser);
                        }
                    }
                    if(titleOrDescriptionChoiceNo == 2){
                        inputFromUser = getStringFromUser(scanner,"Enter new description: ");
                        if(!inputFromUser.trim().equals("")){
                            taskToUpdateTitle.setDescription(inputFromUser);
                        }
                    }
                    if(!inputFromUser.trim().equals("")) {
                        toDoListService.createOrUpdateTask(taskToUpdateTitle);
                    }
                    break;
                case 6:
                    System.out.println("You have exited! :(");
                    break;
            }

        }while (response != 6);

    }

    private String getStringFromUser(Scanner inputScanner,String msg){
        System.out.println(msg);
        return inputScanner.nextLine();
    }
    private int getIntFromUser(Scanner in){
        int response = in.nextInt();
        in.nextLine();
        return response;
    }
    private void showOptions() {
        System.out.println("Choose one option from the list below: \n" +
                            "(1)-to create a todo\n"+
                            "(2)-to view your todos\n" +
                            "(3)-to mark/unmark your todo as completed/incomplete\n" +
                            "(4)-to delete a todo\n"+
                            "(5)-to update the title or description\n"+
                            "(6)-to quit");
    }
    private void insertDefaultData(){
        User user = new User();
        user.setId(Long.valueOf(1));
        user.setUsername(defaultUsername);
        user.setPassword(defaultPass);
        userService.createOrUpdateUser(user);
    }*/
}
