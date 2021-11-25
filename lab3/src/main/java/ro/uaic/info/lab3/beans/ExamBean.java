package ro.uaic.info.lab3.beans;

import lombok.Data;
import lombok.SneakyThrows;
import ro.uaic.info.lab3.dto.Table;
import ro.uaic.info.lab3.entites.Exam;
import ro.uaic.info.lab3.entites.ExamWithResources;
import ro.uaic.info.lab3.repositories.ExamRepository;
import ro.uaic.info.lab3.util.FormHandler;
import ro.uaic.info.lab3.util.TableDataMapper;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Data
@Named
@RequestScoped
public class ExamBean {
    @Inject
    @SuppressWarnings("CdiInjectionPointsInspection")
    private FormHandler<? extends Exam> handler;

    @Inject
    private TableDataMapper<Exam> examTableDataMapper;

    @Inject
    private ExamRepository examRepository;

    private String name;
    private Date start;
    private Long duration;

    @SneakyThrows
    @Transactional
    public void submit() {
        handler.handle(createExam());
    }


    @SuppressWarnings("unchecked")
    private <E extends Exam> E createExam() {
        final Time startTime = new Time(start.getTime());
        return (E) new ExamWithResources(name, startTime, duration, List.of(new ro.uaic.info.lab3.entites.Resource("Manual")));
    }

    public Table getData() {
//        return examTableDataMapper.map(examRepository.findAll());
        var map = new HashMap<String, String>();
        map.put("ASD", "ASD");
        return new Table(List.of("ASD"), List.of(map));
    }

}
