package pl.mateuszlisinski.ex7.service;

import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mateuszlisinski.ex7.entity.Entity;
import pl.mateuszlisinski.ex7.entity.Task;
import pl.mateuszlisinski.ex7.entity.TaskDto;
import pl.mateuszlisinski.ex7.enums.Category;
import pl.mateuszlisinski.ex7.mapper.TaskMapper;
import pl.mateuszlisinski.ex7.repository.TaskRepository;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository repository;
    private final TaskMapper mapper;

    @Autowired
    public TaskService(TaskRepository repository, TaskMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public void create(TaskDto dto) {
        Task task = mapper.toEntity(dto);
        repository.save(task);
    }

    public TaskDto find(Integer id) {
        Task task = repository.findById(id);
        return mapper.toDto(task);
    }

    public List<TaskDto> findAll() {
        Collection<Task> tasks = repository.findAll();
        return tasks.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public List<TaskDto> findHighestPriority() {
        Collection<Task> tasks = repository.findAll();
        return tasks.stream()
                .map(mapper::toDto)
                .filter(task -> task.getPriority().equals(1))
                .collect(Collectors.toList());
    }

    public List<TaskDto> findTommorow() {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        Collection<Task> tasks = repository.findAll();
        return tasks.stream()
                .map(mapper::toDto)
                .filter(task -> task.getDate().getDayOfMonth() == tomorrow.getDayOfMonth())
                .collect(Collectors.toList());
    }

    public List<TaskDto> sortPriority() {
        Collection<Task> tasks = repository.findAll();
        return tasks.stream()
                .map(mapper::toDto)
                .sorted(Comparator.comparing(TaskDto::getPriority))
                .collect(Collectors.toList());
    }

    public List<TaskDto> sortDate() {
        Collection<Task> tasks = repository.findAll();
        return tasks.stream()
                .map(mapper::toDto)
                .sorted(Comparator.comparing(TaskDto::getDate))
                .collect(Collectors.toList());
    }

    public List<TaskDto> findCategory(Category category) {
        Collection<Task> tasks = repository.findAll();
        return tasks.stream()
                .map(mapper::toDto)
                .filter(task -> task.getCategory().equals(category))
                .collect(Collectors.toList());
    }

    public List<TaskDto> searchByParam(String description) {
        Collection<Task> tasks = repository.findAll();
        return tasks.stream()
                .map(mapper::toDto)
                .filter(task -> task.getDescription().toLowerCase(Locale.ROOT).contains(description))
                .collect(Collectors.toList());
    }

    public Optional<TaskDto> filterHighPriorityAndEarliest() {
        Collection<Task> tasks = repository.findAll();
        Optional <TaskDto> optionalTaskDto = tasks.stream()
                .map(mapper::toDto)
                .min(Comparator.comparing(TaskDto::getDate).thenComparing(TaskDto::getDate))
                .stream().findFirst();
        return optionalTaskDto;
    }

    public Map<Category,List<TaskDto>> sortCategoryAndTask() {
        Collection<Task> tasks = repository.findAll();
        List<TaskDto> listDto= new ArrayList<>(tasks.stream().map(mapper::toDto).collect(Collectors.toList()));
        Map<Category,List<TaskDto>> taskMap = new HashMap<>();
        for (int i = 0; i<TaskListTo2DimensionalList
                (3,listDto).size(); i++){
            for (int j=0; j<TaskListTo2DimensionalList(3,listDto).get(i).size();j++){
                taskMap.put(TaskListTo2DimensionalList(3,listDto).get(i).get(j).getCategory(), TaskListTo2DimensionalList(3,listDto).get(i));
            }
        }
        return taskMap;
    }

    public Map<Integer,List<TaskDto>> sortPriorityAndTask() {
        Collection<Task> tasks = repository.findAll();
        List<TaskDto> listDto= new ArrayList<>(tasks.stream().map(mapper::toDto).collect(Collectors.toList()));
        Map<Integer,List<TaskDto>> taskMap = new HashMap<>();
        for (int i = 0; i<TaskListTo2DimensionalListPriority(5,listDto).size(); i++){
            for (int j=0; j<TaskListTo2DimensionalListPriority(5,listDto).get(i).size();j++){
                taskMap.put(TaskListTo2DimensionalListPriority(5,listDto).get(i).get(j).getPriority(), TaskListTo2DimensionalListPriority(5,listDto).get(i));
            }
        }
        return taskMap;
    }

    public Map<Category,Optional<TaskDto>> findHighestPriorityCategoryOptional(){
        Collection<Task> tasks = repository.findAll();
        List<TaskDto> listDto = new ArrayList<>(tasks.stream()
                .map(mapper::toDto)
                .filter(task -> task.getPriority().equals(1))
                .collect(Collectors.toList()));
        Map<Category,Optional<TaskDto>> taskMap = new HashMap<>();
        for (int i = 0; i<listDto.size();i++){
            taskMap.put(listDto.get(i).getCategory(),Optional.of(listDto.get(i)));
        }
        return taskMap;
    }

    private List<ArrayList<TaskDto>> TaskListTo2DimensionalList
            (int vertexCount, List<TaskDto> listDto) {
        List<ArrayList<TaskDto>> category = new ArrayList<>();
        for (int i = 0; i< vertexCount; i++){
            category.add(new ArrayList<>());
        }
        for (TaskDto task: listDto) {
            switch (task.getCategory()){
                case DOM -> category.get(0).add(task);
                case OGROD -> category.get(1).add(task);
                case PRACA -> category.get(2).add(task);
            }
        }
        return category;
    }

    private List<ArrayList<TaskDto>> TaskListTo2DimensionalListPriority
            (int vertexCount, List<TaskDto> listDto) {
        List<ArrayList<TaskDto>> priorty = new ArrayList<>();
        for (int i = 0; i< vertexCount; i++){
            priorty.add(new ArrayList<>());
        }
        for (TaskDto task: listDto) {
            switch (task.getPriority()){
                case 1 -> priorty.get(0).add(task);
                case 2 -> priorty.get(1).add(task);
                case 3 -> priorty.get(2).add(task);
                case 4 -> priorty.get(3).add(task);
                case 5 -> priorty.get(4).add(task);
            }
        }
        return priorty;
    }
}
