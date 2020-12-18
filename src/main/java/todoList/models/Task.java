package todoList.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String taskTitle;
    private String description;
    @Column(name="IS_MARKED", length = 5, nullable=true)
    private boolean isMarkedAsComplete;
}
