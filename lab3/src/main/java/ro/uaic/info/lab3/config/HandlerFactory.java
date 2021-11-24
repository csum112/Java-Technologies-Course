package ro.uaic.info.lab3.config;

import ro.uaic.info.lab3.entites.Exam;
import ro.uaic.info.lab3.entites.Student;
import ro.uaic.info.lab3.repositories.StudentRepository;
import ro.uaic.info.lab3.util.FormHandler;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

@ApplicationScoped
public class HandlerFactory {
    private final ExamPersistStrategy examPersistStrategy;
    private final StudentRepository studentRepository;

    @Inject
    public HandlerFactory(ExamPersistStrategy examPersistStrategy, StudentRepository studentRepository) {
        this.examPersistStrategy = examPersistStrategy;
        this.studentRepository = studentRepository;
    }

    @Produces
    public FormHandler<Exam> examFormHandler() {
        return examPersistStrategy::save;
    }

    @Produces
    public FormHandler<Student> studentFormHandler() {
        return studentRepository::save;
    }
}
