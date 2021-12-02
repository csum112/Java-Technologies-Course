package ro.uaic.info.lab3.beans;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import ro.uaic.info.lab3.entites.ExamWithResources;
import ro.uaic.info.lab3.entites.Resource;
import ro.uaic.info.lab3.repositories.ExamRepository;
import ro.uaic.info.lab3.repositories.ExamWithResourcesRepository;
import ro.uaic.info.lab3.repositories.ResourceRepository;
import ro.uaic.info.lab3.services.ResourceAssignmentService;
import ro.uaic.info.lab3.util.SearchCriteria;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Data
@Named
@ApplicationScoped
public class TestBean {
    @EJB
    private ResourceRepository resourceRepository;

    @EJB
    private ExamWithResourcesRepository examRepository;

    @EJB
    private ResourceAssignmentService resourceAssignmentService;

    private Resource resource;
    private ExamWithResources exam;

    @PostConstruct
    @Transactional
    public void init() {

        resource = new Resource("Video Projector", 10);
        exam = new ExamWithResources("asd", Time.valueOf(LocalTime.now()), 10L, new ArrayList<>());
        resourceRepository.save(resource);
        examRepository.save(exam);
    }

    public void test() {
//        System.out.println("Test asd asd");
//        System.out.println(
//                examRepository.searchExams(
//                        SearchCriteria.builder()
//                                .examName("asd")
//                                .build()
//                ).toString()
//        );

        resourceAssignmentService.assignResources(exam, List.of(resource));
    }
}
