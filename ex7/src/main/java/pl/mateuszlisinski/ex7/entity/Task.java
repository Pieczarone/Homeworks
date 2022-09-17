package pl.mateuszlisinski.ex7.entity;

import lombok.Getter;
import lombok.Setter;
import pl.mateuszlisinski.ex7.enums.Category;

import java.time.LocalDate;
import java.util.Date;

@Setter
@Getter

public class Task implements Entity<Integer> {
    private Integer id;
    private String description;
    private Category category;
    private Integer priority;
    private LocalDate date;
}
