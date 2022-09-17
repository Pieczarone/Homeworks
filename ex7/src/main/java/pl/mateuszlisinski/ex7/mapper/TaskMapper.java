package pl.mateuszlisinski.ex7.mapper;

import org.springframework.stereotype.Component;
import pl.mateuszlisinski.ex7.entity.Task;
import pl.mateuszlisinski.ex7.entity.TaskDto;

@Component
public class TaskMapper {


    public TaskDto toDto(Task task) {
        TaskDto dto = new TaskDto();
        dto.setId(task.getId());
        dto.setCategory(task.getCategory());
        dto.setDescription(task.getDescription());
        dto.setPriority(task.getPriority());
        dto.setDate(task.getDate());
        return dto;
    }

    public Task toEntity(TaskDto taskDto) {
        Task entity = new Task();
        entity.setId(taskDto.getId());
        entity.setCategory(taskDto.getCategory());
        entity.setDate(taskDto.getDate());
        entity.setDescription(taskDto.getDescription());
        entity.setPriority(taskDto.getPriority());
        return entity;
    }
}
