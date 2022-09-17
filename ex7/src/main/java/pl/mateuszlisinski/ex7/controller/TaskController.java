package pl.mateuszlisinski.ex7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.mateuszlisinski.ex7.entity.Task;
import pl.mateuszlisinski.ex7.entity.TaskDto;
import pl.mateuszlisinski.ex7.enums.Category;
import pl.mateuszlisinski.ex7.service.TaskService;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TaskController {
    
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("")
    public String getTasks(Model model) {
        model.addAttribute("tasks", taskService.findAll());
        return "tasks";
    }

    @GetMapping("/task/new")
    public String getTaskForm(Model model) {
        model.addAttribute("task", new Task());
        return "task-form";
    }

    @GetMapping("tasks/filter/high-priority")
    public String getTasksHighPriority(Model model) {
        model.addAttribute("tasks", taskService.findHighestPriority());
        return "tasks";
    }

    @GetMapping("/tasks/tomorrow")
    public String getTasksTomorrow(Model model) {
        model.addAttribute("tasks", taskService.findTommorow());
        return "tasks";
    }

    @GetMapping("/tasks/sort/priority")
    public String getSortedByPriority(Model model) {
        model.addAttribute("tasks", taskService.sortPriority());
        return "tasks";
    }

    @GetMapping("/tasks/sort/date")
    public String getSortedByDate(Model model) {
        model.addAttribute("tasks", taskService.sortDate());
        return "tasks";
    }

    @GetMapping("tasks/filter/category")
    public String getTaskCategory(Model model) {
        model.addAttribute("tasks", taskService.findCategory(Category.DOM));
        return "tasks";
    }

    @GetMapping("search")
    public String searchTaskByParam(@RequestParam (defaultValue = "") String description, Model model){
        model.addAttribute("tasks", taskService.searchByParam(description));
        return "tasks";
    }

    @GetMapping("/tasks/filter/priority/date")
    public String getHighPriorityAndEarliest(Model model) {
        model.addAttribute("tasks", taskService.filterHighPriorityAndEarliest());
        return "tasks";
    }

//    @GetMapping("/tasks/filter/category/task")
//    public String getTasksFiltredByCategory(){
//        Map<Category,List<TaskDto>> taskMap = new HashMap<>(taskService.sortCategoryAndTask());
//        return "tasks";
//    }

    @PostMapping("/task/new")
    public String sendTask(@Valid @ModelAttribute("task") TaskDto task,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "task-form";
        }
        taskService.create(task);
        return "task-form-success";
    }
}
