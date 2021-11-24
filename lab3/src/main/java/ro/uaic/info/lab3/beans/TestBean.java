package ro.uaic.info.lab3.beans;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import ro.uaic.info.lab3.repositories.ExamRepository;
import ro.uaic.info.lab3.util.SearchCriteria;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Slf4j
@Data
@Named
@RequestScoped
public class TestBean {
    @Inject
    private ExamRepository examRepository;

    public void test() {
        System.out.println("Test asd asd");
        System.out.println(
                examRepository.searchExams(
                        SearchCriteria.builder()
                                .examName("asd")
                                .build()
                ).toString()
        );
    }
}
