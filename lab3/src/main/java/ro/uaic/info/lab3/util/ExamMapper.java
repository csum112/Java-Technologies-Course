package ro.uaic.info.lab3.util;

import ro.uaic.info.lab3.repositories.ExamRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
public class ExamMapper {
    @Inject
    private ExamRepository examRepository;

}
