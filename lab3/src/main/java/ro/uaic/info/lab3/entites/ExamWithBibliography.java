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
public class ExamWithBibliography extends Exam {
    @ManyToMany
    private List<BibliographyItem> bibliographyItems;

    public ExamWithBibliography(String name, Time start, Long duration, List<BibliographyItem> bibliographyItems) {
        super(name, start, duration);
        this.bibliographyItems = bibliographyItems;
    }
}
