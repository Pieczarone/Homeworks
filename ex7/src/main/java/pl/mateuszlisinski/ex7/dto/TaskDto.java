package pl.mateuszlisinski.ex7.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import pl.mateuszlisinski.ex7.enums.Category;
import javax.validation.constraints.*;
import java.time.LocalDate;

@AllArgsConstructor
@Setter
@Getter
@ToString
public class TaskDto {

    public TaskDto (){
    }

    private Integer id;
    @NotEmpty(message = "Nie możesz zostawić pustego pola")
    private String description;
    private Category category;
    @NotNull(message = "Nie możesz zostawić pustego pola")
    @Min(value = 1, message = "Skala wynosi od 1 do 5")
    @Max(value = 5, message = "Skala wynosi od 1 do 5")
    private Integer priority;
    @Future(message = "Nie można wybrać daty wstecz")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Pole z datą nie może być puste")
    private LocalDate date;


}
