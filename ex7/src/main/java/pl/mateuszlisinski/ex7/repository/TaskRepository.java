package pl.mateuszlisinski.ex7.repository;

import org.springframework.stereotype.Repository;
import pl.mateuszlisinski.ex7.entity.Task;
import pl.mateuszlisinski.ex7.enums.Category;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class TaskRepository extends FakeRepository<Integer, Task> {
    private final AtomicInteger nextId = new AtomicInteger(1);

    @PostConstruct
    public void initDb() {
        Task cleaning = new Task();
        cleaning.setDescription("Sprzątanie");
        cleaning.setCategory(Category.DOM);
        cleaning.setPriority(1);
        cleaning.setDate(LocalDate.of(2022, 1,24));
        save(cleaning);

        Task homework = new Task();
        homework.setDescription("Praca domowa");
        homework.setCategory(Category.DOM);
        homework.setPriority(3);
        homework.setDate(LocalDate.of(2022, 1,29));
        save(homework);

        Task meeting = new Task();
        meeting.setDescription("Spotkanie");
        meeting.setCategory(Category.PRACA);
        meeting.setPriority(4);
        meeting.setDate(LocalDate.of(2022,1,30));
        save(meeting);

        Task cutWood = new Task();
        cutWood.setDescription("Zrąbać drewno");
        cutWood.setCategory(Category.OGROD);
        cutWood.setPriority(1);
        cutWood.setDate(LocalDate.of(2022,1,25));
        save(cutWood);

        Task cleaningRoom = new Task();
        cleaningRoom.setDescription("Posprzątać pokój");
        cleaningRoom.setCategory(Category.DOM);
        cleaningRoom.setPriority(2);
        cleaningRoom.setDate(LocalDate.of(2022,1,24));
        save(cleaningRoom);
    }

    @Override
    Integer nextId() {
        return nextId.getAndIncrement();
    }


}
