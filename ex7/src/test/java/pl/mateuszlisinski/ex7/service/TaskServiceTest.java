package pl.mateuszlisinski.ex7.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import pl.mateuszlisinski.ex7.entity.Task;
import pl.mateuszlisinski.ex7.entity.TaskDto;
import pl.mateuszlisinski.ex7.enums.Category;
import pl.mateuszlisinski.ex7.mapper.TaskMapper;
import pl.mateuszlisinski.ex7.repository.FakeRepository;
import pl.mateuszlisinski.ex7.repository.TaskRepository;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;


class TaskServiceTest {

    private final TaskRepository taskRepository = new TaskRepository();

    private final TaskMapper taskMapper = new TaskMapper();

    private final TaskService taskService = new TaskService(taskRepository,taskMapper);

    private List<TaskDto> dtoList;

    

    @BeforeEach
    void setUp(){
        taskRepository.initDb();
    }

    @Test
    void findHighestPriority() {

       dtoList= taskService.findHighestPriority();
        for (TaskDto task: dtoList) {
            assertThat(task.getPriority()).isEqualTo(1);
        }
    }

    @Test
    void findTommorow() {
        LocalDate localDate = LocalDate.now().plusDays(1);
        dtoList = taskService.findTommorow();
        for (TaskDto task: dtoList) {
            assertThat(task.getDate()).isEqualTo(localDate);
        }
    }

    @Test
    void sortPriority() {
        dtoList = taskService.sortPriority();
        for (int i = 0; i<dtoList.size()-1;i++){
            assertThat(dtoList.get(i).getPriority()).isLessThan(dtoList.get(i+1).getPriority());
        }
    }

    @Test
    void sortDate() {
        dtoList = taskService.sortDate();
        for (int i = 0; i < dtoList.size() - 1; i++) {
            assertThat(dtoList.get(i).getDate()).isBeforeOrEqualTo(dtoList.get(i+1).getDate());
        }
    }
    @Test
    void findCategory() {
        dtoList = taskService.findCategory(Category.DOM);
        for (TaskDto task: dtoList) {
            assertThat(task.getCategory()).isEqualTo(Category.DOM);
        }
    }

    @Test
    void searchByParam() {
        dtoList = taskService.searchByParam("Sprzątanie");
        for(TaskDto task : dtoList){
            assertThat(task.getDescription()).isEqualTo("Sprzątanie");
        }
    }

    @Test
    void filterHighPriorityAndEarliest() {
        Optional<TaskDto> taskDto = taskService.filterHighPriorityAndEarliest();
        Task expectedResult = new Task();
        expectedResult.setDescription("Sprzątanie");
        expectedResult.setCategory(Category.DOM);
        expectedResult.setPriority(1);
        expectedResult.setDate(LocalDate.of(2022, 1,24));
        expectedResult.setId(1);
        Optional <TaskDto> optionalExpected = Optional.of(expectedResult).map(taskMapper::toDto);
        assertThat(taskDto.toString()).isEqualTo(optionalExpected.toString());



    }

    @Test
    void sortCategoryAndTask() {
        Map<Category,List<TaskDto>> taskDtoMap = taskService.sortCategoryAndTask();
        for (Map.Entry<Category, List<TaskDto>> entry : taskDtoMap.entrySet()){
            for(int i = 0; i<entry.getValue().size(); i++){
                assertThat(entry.getKey()).isEqualTo(entry.getValue().get(i).getCategory());
            }

        }
    }

    @Test
    void sortPriorityAndTask() {
        Map<Integer, List<TaskDto>> taskDtoMap = taskService.sortPriorityAndTask();
        for (Map.Entry<Integer, List<TaskDto>> entry : taskDtoMap.entrySet()){
            for (int i = 0; i<entry.getValue().size();i++){
                assertThat(entry.getKey()).isEqualTo(entry.getValue().get(i).getPriority());
            }
        }
    }

    @Test
    void findHighestPriorityCategoryOptional() {
        Map<Category,Optional<TaskDto>> taskMap = taskService.findHighestPriorityCategoryOptional();
        for(Map.Entry <Category, Optional<TaskDto>> entry: taskMap.entrySet()){
            for(int i = 0; i<3;i++){
                assertThat(entry.getKey()).isEqualTo(entry.getValue().get().getCategory());
                assertThat(entry.getValue().get().getPriority()).isEqualTo(1);
            }
        }
    }
}