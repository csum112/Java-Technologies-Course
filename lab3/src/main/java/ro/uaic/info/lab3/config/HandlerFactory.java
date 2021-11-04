package ro.uaic.info.lab3.config;

import ro.uaic.info.lab3.entites.Exam;
import ro.uaic.info.lab3.entites.Student;
import ro.uaic.info.lab3.repositories.ExamRepository;
import ro.uaic.info.lab3.repositories.StudentRepository;
import ro.uaic.info.lab3.util.FormHandler;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.sql.SQLException;

@ApplicationScoped
public class HandlerFactory {
    private final ExamRepository examRepository;
    private final StudentRepository studentRepository;

    @Inject
    public HandlerFactory(ExamRepository examRepository, StudentRepository studentRepository) {
        this.examRepository = examRepository;
        this.studentRepository = studentRepository;
    }

    @Produces
    public FormHandler<Exam> examFormHandler() {
        return (exam) -> {
            try {
                this.examRepository.save(exam);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        };
    }

    @Produces
    public FormHandler<Student> studentFormHandler() {
        return (student) -> {
            try {
                studentRepository.save(student);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        };
    }
}
