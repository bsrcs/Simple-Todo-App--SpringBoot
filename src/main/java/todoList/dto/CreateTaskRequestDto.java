package todoList.dto;

import lombok.Data;

@Data
public class CreateTaskRequestDto {
    private Long userId;
    private String taskTitle;
    private String description;
}
