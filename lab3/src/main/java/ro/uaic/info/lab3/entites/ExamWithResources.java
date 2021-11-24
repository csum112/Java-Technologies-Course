package ro.uaic.info.lab3.entites;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.sql.Time;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ExamWithResources extends Exam{
    @ManyToMany
    private List<Resource> resources;

    public ExamWithResources(String name, Time start, Long duration, List<Resource> resources) {
        super(name, start, duration);
        this.resources = resources;
    }
}
