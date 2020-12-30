package todoList.dto;

import lombok.Data;

@Data
// you must create dto to not let others to see password and username of user :)
public class CreateTaskResponseDto {
    private Long userId;
    private Long taskId;
    private String taskTitle;
    private String description;
}
