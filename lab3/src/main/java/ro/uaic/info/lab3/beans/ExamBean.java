package ro.uaic.info.lab3.beans;

import lombok.Data;
import ro.uaic.info.lab3.entites.Exam;
import ro.uaic.info.lab3.util.FormHandler;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.sql.Time;
import java.time.Duration;
import java.util.Date;

@Data
@Named
@RequestScoped
public class ExamBean {
    @Inject
    private FormHandler<Exam> handler;
    private String name;
    private Time start;
    private Long duration;

    public void submit() {
        handler.handle(new Exam(name, start, duration));
    }
}
