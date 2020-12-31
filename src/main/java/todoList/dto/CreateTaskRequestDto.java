package todoList.dto;

import lombok.Data;

@Data
public class CreateTaskRequestDto {
    private String taskTitle;
    private String description;
}
